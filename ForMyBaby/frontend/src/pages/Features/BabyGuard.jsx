import React, { useState, useEffect } from 'react';
import socketIOClient from 'socket.io-client';
import NavBar from '../../components/NavBar';
import DetailContent from './BabyGuardDetail';
import ChangeContent from './BabyGuardChange';
import SleepStatusContent from './SleepStatusContent'; 
import { useRecordStore } from '../../stores/RecordStore'; 
import './BabyGuard.css';
import { getTodayData } from '../../api/sleepApi';
import { useUserStore } from '../../stores/UserStore';
import { useLocation } from 'react-router-dom';

const ENDPOINT = 'http://localhost:3001';

const ImageContent = ({ imageData, lineData }) => (
  <div className="image-content">
    <h1>Received Image</h1>
    {imageData && <img src={imageData} alt="Received" />}
    {lineData && <p>Received Line: {lineData}</p>}
  </div>
);

const Dashboard = () => {
  const [imageData, setImageData] = useState(null);
  const [lineData, setLineData] = useState('');
  const [timestamp, setTimestamp] = useState('');
  const [selectedButton, setSelectedButton] = useState('button1');
  const { babySelected } = useUserStore();

  const { danger, hours, awake } = useRecordStore(); // 상태 관리 파일에서 상태 가져오기

  const location = useLocation();
  const queryParams = new URLSearchParams(location.search);

  // 선택된 버튼 상태를 확인합니다.
  const getButton = queryParams.get('selectedButton');

  const handleButtonClick = (buttonName) => {
    setSelectedButton(buttonName);
  };

  useEffect(() => {
    const fetchData = async () => {
      if (getButton != null){
        handleButtonClick('button2');
      }
      if (danger === null || awake === null || hours === null) {
        try {
          // API 호출하여 데이터 가져오기
          const response = await getTodayData();
          // 가져온 데이터를 상태로 업데이트
          useRecordStore.setState({
            danger: response.dangerCnt,
            awake: response.sleepCnt,
            hours: response.sleepTime
          });
        } catch (error) {
          console.error('Error fetching data:', error);
        }
      }
    };
  
    fetchData();

    const socket = socketIOClient(ENDPOINT, {
      transports: ['websocket'],
    });

    socket.emit('babyId', babySelected);

    socket.on('image', ({ imageData, lineData, timestamp }) => {
      const base64String = btoa(
        new Uint8Array(imageData).reduce((data, byte) => data + String.fromCharCode(byte), '')
      );
      setImageData(`data:image/jpeg;base64,${base64String}`);
      setLineData(lineData);
      setTimestamp(timestamp);
      console.log('온습도 데이터', lineData);
      console.log('시간', timestamp);
    });

    // 소켓 위험 알림용 이벤트 수신
    socket.on('dangerEvent', () => {
      // 위험행동 상태 업데이트
      useRecordStore.setState({ danger: danger + 1 });
    });

    // 소켓 수면 분석용 이벤트 수신
    socket.on('sleepEvent', () => {
      // 깨어남 상태 업데이트
      useRecordStore.setState({ awake: awake + 1 });
    });

    return () => {};
  }, []);

  return (
    <div className="dashboard">
      <div className="sleep-title">우리 아이 지킴이</div>
      <div className="button-container">
        <button className={selectedButton === 'button1' ? 'bold' : ''} onClick={() => handleButtonClick('button1')}>대시보드</button>
        <button className={selectedButton === 'button2' ? 'bold' : ''} onClick={() => handleButtonClick('button2')}>상세</button>
        <button className={selectedButton === 'button3' ? 'bold' : ''} onClick={() => handleButtonClick('button3')}>변화</button>
      </div>
      <div className="babyguard-content">
        {selectedButton === 'button1' && (
          <div className="dashboard-content">
            <div className="video-content" >
              <h1>Real-time Video</h1>
              {/* 이미지가 없는 경우를 처리하여 메시지 표시 */}
              {!imageData && <p style={{ color: '#666' }}>No video available</p>}
              {imageData && <img src={imageData} alt="Received" style={{ maxWidth: '100%', maxHeight: '100%' }} />}
            </div>
            <SleepStatusContent danger={danger} hours={hours} awake={awake} lineData={lineData} /> {/* 수정된 부분 */}
          </div>
        )}
        {selectedButton === 'button2' && <DetailContent />}
        {selectedButton === 'button3' && <ChangeContent />}
      </div>
      <NavBar />
    </div>
  );
};

export default Dashboard;
