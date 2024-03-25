import cv2
import sys
import re
import serial as pyserial
from io import BytesIO

from collections import namedtuple

# Define a namedtuple class for your sensor data
SensorData = namedtuple('SensorData', ['aX', 'aY', 'aZ', 'gX', 'gY', 'gZ', 'Tmp', 'dh', 'dt'])
FeatureData = namedtuple('FeatureData', ['f1', 'f2', 'rtn'])

class MySensor:
    def __init__(self, name):
        self.name = name
        self.cam = self.webcam_open()
        self.ser = self.serial_connect()

    def __str__(self):
        return self.name

    def webcam_open(self):
        try:
            webcam = cv2.VideoCapture(0)
            print('Webcam is opened.')
            return webcam
        except Exception as e:
            print('Cannot open the webcam.')
            print(str(e))
            sys.exit(1)


    def frame_capture(self):
        self.ret, self.frame = self.cam.read()

    def frame_encode(self):
        try:
            _, self.jpeg = cv2.imencode('.jpg', self.frame)
            self.jpeg_bytes = jpeg.tobytes()
            self.jpeg_file = BytesIO(jpeg_bytes)
            return self.jpeg_file
        except Exception as e:
            print('Cannot encode the frame.')
            print(str(e))
            return None

    def webcam_release(self):
        self.cam.release()
        cv2.destroyAllWindows()

            
    def serial_connect(self):
        try:
            ser = pyserial.Serial('/dev/ttyACM0', 9600)
            return ser
        except pyserial.SerialException:
            print("Cannot start serial communication.")
            return None
        
    def data_get(self):
        # Read line data from serial port
        if self.ser.in_waiting > 0:
            self.line = self.ser.readline().decode('utf-8').rstrip()

    def data_raw(self):
        if self.line:
            # Use regular expressions to parse the sensor values from the line.
            match = re.match(r'(.*) , (.*) , (.*) , (.*) , (.*) , (.*) , (.*) , (.*) , (.*)', self.line)

            if match:
                self.aX, self.aY, self.aZ, self.gX, self.gY, self.gZ, self.Tmp, self.dh, self.dt = map(float, match.groups())
                self.data = SensorData(self.aX, self.aY, self.aZ, self.gX, self.gY, self.gZ, self.Tmp, self.dh, self.dt)
            else:
                self.data = None

    def data_preprocess(self):
        self.f1 = (self.data.aX**2 + self.data.aY**2 + self.data.aZ**2)**0.5
        self.f2 = (self.data.gX**2 + self.data.gY**2 + self.data.gZ**2)**0.5


        
    def __del__(self):
        self.webcam_release()
        if self.ser:
            self.ser.close()
