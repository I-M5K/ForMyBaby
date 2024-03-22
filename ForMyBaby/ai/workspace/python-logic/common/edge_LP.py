
import requests
from my_sensor import MySensorData

class NetworkManager:
    def __init__(self, ip, port, baby_id):
        self.ip = ip
        self.port = port
        self.url = f'http://{self.ip}:{self.port}/data'
        self.baby_id = baby_id
        self.sensor_data = MySensorData()
        
    def connect_to_server(self):
        print("Connecting to server...")
        # Add logic to connect to the server
        self.send_data_to_server()

    def send_data_to_server(self):
         # Send POST request to the server
        try:
            response = requests.post(self.url, files={'image': ('image.jpg', self.sensor_data.frame_encode(), 'image/jpeg')}, data={'line': self.sensor_data.data_raw(), 'baby_id': self.baby_id})

        except requests.exceptions.RequestException as e:
            print(e)
