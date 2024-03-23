import React, { useState, useEffect } from 'react';
import socketIOClient from 'socket.io-client';
import NavBar from '../../components/NavBar';

import './BabyGuard.css';

const ENDPOINT = 'http://localhost:3001'; // 서버의 엔드포인트에 맞게 수정

const ClientComponent = () => {
  const [imageData, setImageData] = useState(null);
  const [lineData, setLineData] = useState('');
  const [babyId, setBabyId] = useState(1); // 기본 아이디 설정

  useEffect(() => {
    const socket = socketIOClient(ENDPOINT, {
      transports: ['websocket'], // WebSocket 프로토콜 강제 사용
    });

    socket.emit('babyId', babyId); // 클라이언트가 babyId를 소켓 서버로 전송

    socket.on('image', ({ imageData, lineData }) => {
      // ArrayBuffer를 Base64로 변환하여 이미지 데이터 업데이트
      const base64String = btoa(
        new Uint8Array(imageData).reduce((data, byte) => data + String.fromCharCode(byte), '')
      );
      setImageData(`data:image/jpeg;base64,${base64String}`);
      // 데이터 업데이트
      setLineData(lineData);
    });

    return () => {
    };
  }, [babyId]); // babyId가 변경될 때마다 소켓 연결 재설정

  return (
    <div className="cctv-container">
      <h1>Received Image</h1>
      {imageData && <img src={imageData} alt="Received" />}
      {lineData && <p>Received Line: {lineData}</p>}
    </div>
  );
};

const App = () => {
  const [selectedButton, setSelectedButton] = useState('button1'); // 디폴트로 'button1' 선택
  const [babyId, setBabyId] = useState('');

  const sleepStatus = {
    movement: 80,
    awake: 20,
    danger: 10,
    temperature: 25,
    humidity: 60
  };

  const handleButtonClick = (buttonName) => {
    setSelectedButton(buttonName);
  };

  const handleBabyIdChange = (event) => {
    setBabyId(event.target.value);
  };

  return (
    <div className="app">
      <p className="title">우리 아이 지킴이</p>
      <div className="button-container">
        <button className={selectedButton === 'button1' ? 'bold' : ''} onClick={() => handleButtonClick('button1')}>대시보드</button>
        <button className={selectedButton === 'button2' ? 'bold' : ''} onClick={() => handleButtonClick('button2')}>상세</button>
        <button className={selectedButton === 'button3' ? 'bold' : ''} onClick={() => handleButtonClick('button3')}>변화</button>
      </div>
      {selectedButton === 'button1' && (
        <>
          {/* <input type="text" value={babyId} onChange={handleBabyIdChange} placeholder="Enter Baby ID" /> */}
          <ClientComponent babyId={babyId} />
        </>
      )} {/* 대시보드가 선택되었을 때만 ClientComponent를 렌더링 */}
      <div className="sleep-info">
        <p className='sleep-title'>수면 정보</p>
        <div className="sleep-status">
          {Object.entries(sleepStatus).map(([key, value]) => (
            <div className="sleep-status-item" key={key}>
              <p className="sleep-status-label">{key}</p>
              <div className="sleep-status-bar">
                <div
                  className="sleep-status-bar-fill"
                  style={{ width: `${value}%`, backgroundColor: value > 50 ? '#5cb85c' : '#d9534f' }}
                />
              </div>
            </div>
          ))}
        </div>
      </div>
      <NavBar />
    </div>
  );
};

export default App;
