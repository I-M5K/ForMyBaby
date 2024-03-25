import React, { useState, useEffect } from 'react';
import socketIOClient from 'socket.io-client';
import NavBar from '../../components/NavBar';
import DetailContent from './BabyGuardDetail';
import ChangeContent from './BabyGuardChange';

import './BabyGuard.css';

const ENDPOINT = 'http://localhost:3001';

const ImageContent = ({ imageData, lineData }) => (
  <div className="image-content">
    <h1>Received Image</h1>
    {imageData && <img src={imageData} alt="Received" />}
    {lineData && <p>Received Line: {lineData}</p>}
  </div>
);

const SleepStatusContent = ({ sleepStatus }) => (
  <div className="sleep-status-content">
    <p className='sleep-title'>오늘의 수면 현황</p>
    <div className="sleep-status">
      {Object.entries(sleepStatus).map(([key, value]) => (
        <div className="sleep-status-item" key={key}>
          <p className="sleep-status-label">{key}</p>
          <div
            className="sleep-status-bar"
            style={{ backgroundColor: value > 50 ? '#5cb85c' : '#d9534f' }}
          >
            <div className="sleep-status-bar-fill" style={{ width: `${value}%` }} />
          </div>
        </div>
      ))}
    </div>
  </div>
);

const Dashboard = () => {
  const [imageData, setImageData] = useState(null);
  const [lineData, setLineData] = useState('');
  const [selectedButton, setSelectedButton] = useState('button1');
  const [babyId, setBabyId] = useState('');
  const [sleepStatus, setSleepStatus] = useState({
    수면시간: 80,
    깨어남: 20,
    위험행동: 10,
    온도: 25,
    습도: 60
  });

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

    socket.on('image', ({ imageData, lineData }) => {
      const base64String = btoa(
        new Uint8Array(imageData).reduce((data, byte) => data + String.fromCharCode(byte), '')
      );
      setImageData(`data:image/jpeg;base64,${base64String}`);
      setLineData(lineData);
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
            <ImageContent imageData={imageData} lineData={lineData} />
            <SleepStatusContent sleepStatus={sleepStatus} />
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
