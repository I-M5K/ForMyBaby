import React, { useState, useEffect } from 'react';
import socketIOClient from 'socket.io-client';
import NavBar from '../../components/NavBar';
import DetailContent from './BabyGuardDetail';
import ChangeContent from './BabyGuardChange';
import SleepStatusContent from './SleepStatusContent'; // 수정된 부분
import { useRecordStore } from '../../stores/RecordStore'; // 상태 관리 파일에서 가져옴

import './BabyGuard.css';

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
  const [babyId, setBabyId] = useState('');

  const { danger, hours, awake } = useRecordStore(); // 상태 관리 파일에서 상태 가져오기


  const handleButtonClick = (buttonName) => {
    setSelectedButton(buttonName);
  };

  const handleBabyIdChange = (event) => {
    setBabyId(event.target.value);
  };

  useEffect(() => {
    const socket = socketIOClient(ENDPOINT, {
      transports: ['websocket'],
    });

    socket.emit('babyId', babyId);

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
  }, [babyId]);

  return (
    <div className="dashboard">
      <p className="title">우리 아이 지킴이</p>
      <div className="button-container">
        <button className={selectedButton === 'button1' ? 'bold' : ''} onClick={() => handleButtonClick('button1')}>대시보드</button>
        <button className={selectedButton === 'button2' ? 'bold' : ''} onClick={() => handleButtonClick('button2')}>상세</button>
        <button className={selectedButton === 'button3' ? 'bold' : ''} onClick={() => handleButtonClick('button3')}>변화</button>
      </div>
      <div className="content">
        {selectedButton === 'button1' && (
          <div className="dashboard-content">
            <div className="video-content" style={{ backgroundColor: imageData ? 'transparent' : '#f0f0f0', width: '80vw', height: '70vh', display: 'flex', justifyContent: 'center', alignItems: 'center' }}>
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
