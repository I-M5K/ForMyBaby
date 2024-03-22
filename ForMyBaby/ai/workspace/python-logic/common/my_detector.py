# my_detector.py

class MyDetector:
    def __init__(self):
        self.gravity_threshold = 15  # 중력 센서에서의 충격 감지 임계값

    def detect_algorithm(self, gravity):
        """충격을 감지하는 알고리즘"""
        if gravity > 9.9:
            return 1  # 충격이 감지됨
        else:
            return 0  # 충격이 감지되지 않음