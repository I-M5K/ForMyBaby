# my_main.py
from my_sensor import MySensor
from my_detector import MyDetector
import time

ACCIDENT_THRESHOLD =   9.9

class EdgeLP:
    def __init__(self, network_manager):
        
        self.my_edge = {
            "network_manager":network_manager,
            "sensor_data":MySensor(),
            "detector":MyDetector(),
        }

        # self.event_tx_config = { 
        #     '1': self.handle_req_rt_video,   # 영상 및 온습도 데이터 요청 시
        #     '2': self.handle_req_sleep_log,   # 수면 데이터 요청 시
        #     '3': self.handle_res_rt_stamp_frame,   # 특정 동작 감지 시 (성장 스턈프)
        #     '4': self.handle_res_rt_danger,   # 특정 동작 감지 시 (사고 알림)
        # }
    

    def collect_data(self):
        self.my_edge['sensor_data'].get()
        self.check(self.my_edge['sensor_data'].data['ser_data'], self.my_edge['sensor_data'].data['frame'])  # Check for accidents after collecting data
        print("self.my_edge['sensor_data']",self.my_edge['sensor_data'].data['ser_data'])
        # 만약 데이터 요청 시
        if self.my_edge['network_manager'].request_data:
            self.my_edge['network_manager'].send_data_to_server(self.my_edge['sensor_data'].data) 
            self.my_edge['network_manager'].request_data = False



    # 영상, 오디오, 시리얼 통신을 위한 시작 세팅
    def start_data(self):
        print("Starting sensor data...")
        # Add logic to start sensor data acquisition
        self.collect_data()  # Example: Call the collect_data method

    def check(self, ser_data, image_data):
        self.my_edge['detector'].detect_start(ser_data, image_data)
        # if self.my_edge['detector'].is_sleep(ser_data, image_data):
        #     print("ohohoh11")
        #     time.sleep(1)
        # elif self.my_edge['detector'].is_event(ser_data, image_data):
        #     print("ohohoh22")
        #     time.sleep(1)

        
