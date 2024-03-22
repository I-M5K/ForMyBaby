# my_main.py
from my_detector import MyDetector
from my_sensor import MySensorData

from edge_LP import *

class EdgeLP:
    def __init__(self, network_manager):
        
        self.my_edge = {
            "network_manager":network_manager,
            "sensor_data":MySensorData(),
            "detector":MyDetector(),
        }

    def collect_data(self):
        self.my_edge['sensor_data'].get()
        self.my_edge['network_manager'].send_data_to_server(self.my_edge['sensor_data'].data_raw())

    def start_data(self):
        print("Starting sensor data...")
        # Add logic to start sensor data acquisition
        self.collect_data()  # Example: Call the collect_data method
