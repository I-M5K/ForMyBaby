import cv2
import sys
import re
import serial as pyserial
from io import BytesIO
import pyaudio
import audioop
import math
from collections import namedtuple

SensorData = namedtuple('SensorData', ['aX', 'aY', 'aZ', 'gX', 'gY', 'gZ', 'Tmp', 'dh', 'dt'])
FeatureData = namedtuple('FeatureData', ['f1', 'f2', 'rtn'])

class MySensor:
    def __init__(self, name):
        self.name = name
        self.cam = self.webcam_open()
        self.ser = self.serial_connect()
        self.p = self.sound_open()

    def __str__(self):
        return self.name

    def webcam_open(self):
        try:
            return cv2.VideoCapture(0)
        except Exception as e:
            print(f'Cannot open the webcam: {e}')
            sys.exit(1)

    def frame_capture(self):
        self.ret, self.frame = self.cam.read()

    def frame_encode(self):
        try:
            _, self.jpeg = cv2.imencode('.jpg', self.frame)
            return BytesIO(self.jpeg.tobytes())
        except Exception as e:
            print(f'Cannot encode the frame: {e}')
            return None


    def webcam_release(self):
        self.cam.release()
        cv2.destroyAllWindows()

    def sound_open(self):
        try:
            return pyaudio.PyAudio()
        except Exception as e:
            print(f'Cannot open the sound device: {e}')
            return None

    def sound_get(self):
        chunk = 1024
        sample_format = pyaudio.paInt16
        channels = 1
        fs = 44100
        stream = self.p.open(format=sample_format, channels=channels, rate=fs, frames_per_buffer=chunk, input=True)
        data = stream.read(chunk)
        return 20 * math.log10(audioop.rms(data, 2))

    def sound_close(self):
        self.stream.stop_stream()
        self.stream.close()
        self.p.terminate()
        
    def serial_connect(self):
        try:
            return pyserial.Serial('/dev/ttyACM0', 9600)
        except pyserial.SerialException:
            print("Cannot start serial communication.")
            return None

    def data_get(self):
        if self.ser.in_waiting > 0:
            self.line = self.ser.readline().decode('utf-8').rstrip()

    def data_raw(self):
        if self.line:
            match = re.match(r'(.*) , (.*) , (.*) , (.*) , (.*) , (.*) , (.*) , (.*) , (.*)', self.line)
            if match:
                self.data = SensorData(*map(float, match.groups()))
            else:
                self.data = None

    def data_preprocess(self):
        self.f1 = (self.data.aX**2 + self.data.aY**2 + self.data.aZ**2)**0.5
        self.f2 = (self.data.gX**2 + self.data.gY**2 + self.data.gZ**2)**0.5

    def __del__(self):
        self.webcam_release()
        if self.ser:
            self.ser.close()