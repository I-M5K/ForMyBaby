# my_detector.py
from scipy.spatial.transform import Rotation as R
from scipy.spatial import distance as dist
import numpy as np
import cv2
import mediapipe as mp

class MyDetector:
    def __init__(self):
        self.treshold = [9.9, 9.9]

        self.feature = [0, 0]
        self.euler = [0, 0, 0]
        self.raw = [0, 0, 0, 0, 0, 0]

    def extract_features(self, data):
        self.raw = [data.aX, data.aY, data.aZ, data.gX, data.gY, data.gZ]
        self.raw[0] = data.aX/32768.0 * 16
        self.raw[1] = data.aY/32768.0 * 16
        self.raw[2] = data.aZ/32768.0 * 16
        self.raw[3] = data.gX/32768.0 * 1000
        self.raw[4] = data.gY/32768.0 * 1000
        self.raw[5] = data.gZ/32768.0 * 1000

        self.feature[0] = (data.aX**2 + data.aY**2 + data.aZ**2)**0.5
        self.feature[1] = (data.gX**2 + data.gY**2 + data.gZ**2)**0.5

        self.euler = self.convert_to_euler([self.raw[0], self.raw[1], self.raw[2]], [self.raw[3], self.raw[4], self.raw[5]])
        self.ser_data = []
        self.image_data = ""
    def detect_start(self, ser_data, image_data):
        self.ser_data = ser_data
        self.image_data = image_data

        self.state = 0 # 0: normal, 1: sleep, 2: event, 3: accident

        if self.is_sleep():
            self.state = 1
            print("자는 중...")
        else:
            print("!!!!!!!!!!자는 중이 아님!!!!!!!!!!")

        
        



        
    # 이미지에서 눈이 감겨있으면 수면으로 판단, 눈이 감겨있지 않으면 수면이 아님
    def is_sleep(self):
        """수면을 감지하는 함수"""
        if self.image_data.size == 0:
            print(self.image_data.size)
            return False
        return self.ai_algo_image()

    def is_event(self):
        """사건을 감지하는 함수"""
        if not self.image_data:
            return False
        return self.feature[1] > self.treshold[1]
    
    def is_accident(self):
        """사고를 감지하는 함수"""
        if not self.image_data:
            return False
        return self.feature[0] > self.treshold[0]
      





    def LPF(self):
        # CutOffFrequency is 10hz
        # SamplingFrequency is 1/0.001 => 1000

        CutOffFrequency = 10.0
        SamplingFrequency = 500.0

        w0 = 2 * 3.14 * CutOffFrequency
        a1 = (w0 - 2 * SamplingFrequency) / (2 * SamplingFrequency + w0)
        b0 = w0 / (2 * SamplingFrequency + w0)
        b1 = b0

        self.Output = b0 * self.Input + b1 * self.PastInput - a1 * self.PastOutput
        self.PastOutput = self.Output
        self.PastInput = self.Input

        return self.Output, self.PastInput, self.PastOutput

    def convert_to_euler(self, accel_data, gyro_data):
        # Assuming accel_data and gyro_data are numpy arrays of shape (3,)
        # and represent acceleration and angular velocity respectively

        # First, we normalize the accelerometer data
        accel_data = accel_data / np.linalg.norm(accel_data)

        # Assuming that the device is not accelerating, the accelerometer data can be considered as the orientation
        # We can convert this orientation into a quaternion
        accel_quat = R.from_rotvec(accel_data).as_quat()

        # Next, we integrate the gyroscope data over time to get the orientation
        # This is a very simple integration and might not be accurate over long periods of time
        gyro_quat = R.from_rotvec(gyro_data).as_quat()

        # We combine the two quaternions to get a more accurate orientation
        combined_quat = accel_quat * gyro_quat

        # Finally, we convert the quaternion to Euler angles
        euler_angles = R.from_quat(combined_quat).as_euler('xyz')

        return euler_angles  # This will return [roll, pitch, yaw]


    def calculate_ear(self,eye):
        # compute the euclidean distances between the two sets of
        # vertical eye landmarks (x, y)-coordinates
        print(eye[1], eye[5])
        # A = dist.euclidean(eye[1][:2], eye[5][:2])
        # B = dist.euclidean(eye[2][:2], eye[4][:2])
        
        A = dist.euclidean([eye[1].x, eye[1].y], [eye[5].x, eye[5].y])
        B = dist.euclidean([eye[2].x, eye[2].y], [eye[4].x, eye[4].y])
        # compute the euclidean distance between the horizontal
        # eye landmark (x, y)-coordinates
        # C = dist.euclidean(eye[0][:2], eye[3][:2])
        C = dist.euclidean([eye[0].x, eye[0].y], [eye[3].x, eye[3].y])

        # compute the eye aspect ratio
        ear = (A + B) / (2.0 * C)

        # return the eye aspect ratio
        return ear

    def ai_algo_image(self):
        # mediapipe를 사용하여 눈을 감고 있는지 판단
        mp_face_mesh = mp.solutions.face_mesh

        
        # For static images:
        with mp_face_mesh.FaceMesh(
            static_image_mode=True,
            max_num_faces=1,
            min_detection_confidence=0.5) as face_mesh:
            image = self.image_data
            # Convert the BGR image to RGB before processing.
            results = face_mesh.process(cv2.cvtColor(image, cv2.COLOR_BGR2RGB))

            # If no face detected, return False
            if not results.multi_face_landmarks:
                return False

            # threshold to determine if the eye is closed
            EAR_THRESHOLD = 0.3
            LEFT_EYE_INDICES = [33, 7, 163, 144, 145, 153, 154, 155, 133]
            RIGHT_EYE_INDICES = [362, 263, 249, 390, 373, 374, 380, 381, 382]
            for face_landmarks in results.multi_face_landmarks:
                
                # Get the coordinates of left and right eye landmarks
                left_eye = [face_landmarks.landmark[i] for i in LEFT_EYE_INDICES]
                right_eye = [face_landmarks.landmark[i] for i in RIGHT_EYE_INDICES]

                # Calculate EAR for left and right eye
                leftEAR = self.calculate_ear(left_eye)
                rightEAR = self.calculate_ear(right_eye)

                # Average the eye aspect ratio together for both eyes
                ear = (leftEAR + rightEAR) / 2.0

                # Check if the eye aspect ratio is below the blink threshold
                if ear < EAR_THRESHOLD:
                    return True  # Eye is closed

            return False  # Eye is open




# import cv2
# import mediapipe as mp

# # Initialize MediaPipe FaceMesh
# mp_face_mesh = mp.solutions.face_mesh
# face_mesh = mp_face_mesh.FaceMesh()

# # Load an image
# image = cv2.imread('path_to_your_image.jpg')

# # Convert the image to RGB
# image_rgb = cv2.cvtColor(image, cv2.COLOR_BGR2RGB)

# # Process the image to detect facial landmarks
# results = face_mesh.process(image_rgb)

# # Check if any face is detected
# if results.multi_face_landmarks:
#     # Draw facial landmarks on the image
#     for face_landmarks in results.multi_face_landmarks:
#         mp.solutions.drawing_utils.draw_landmarks(
#             image, face_landmarks, mp_face_mesh.FACE_CONNECTIONS)

# # Display the image
# cv2.imshow('Image with Facial Landmarks', image)
# cv2.waitKey(0)
# cv2.destroyAllWindows()