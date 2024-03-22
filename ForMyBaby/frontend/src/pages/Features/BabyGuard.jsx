import React, { useState, useEffect } from 'react';
import socketIOClient from 'socket.io-client';
import NavBar from '../../components/NavBar';

import './BabyGuard.css';

const ENDPOINT = 'http://localhost:3001'; // 서버의 엔드포인트에 맞게 수정

const ClientComponent = () => {
  const [imageData, setImageData] = useState(null);

  useEffect(() => {
    const socket = socketIOClient(ENDPOINT, {
      transports: ['websocket'], // WebSocket 프로토콜 강제 사용
    });

    socket.on('image', (data) => {
      // ArrayBuffer를 Base64로 변환
      const base64String = btoa(
        new Uint8Array(data).reduce((data, byte) => data + String.fromCharCode(byte), '')
      );
      // 이미지 데이터를 Base64로 인코딩하여 상태 업데이트
      setImageData(`data:image/jpeg;base64,${base64String}`);
    });

    return () => {
      socket.disconnect(); // 컴포넌트가 언마운트될 때 소켓 연결 종료
    };
  }, []); // 페이지가 처음 로드될 때 한 번만 실행

  return (
    <div className="cctv-container">
      <h1>Received Image</h1>
      {imageData && <img src={imageData} alt="Received" />}
    </div>
  );
};

const App = () => {
  const [selectedButton, setSelectedButton] = useState('button1'); // 디폴트로 'button1' 선택

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

  return (
    <div className="app">
      <p className="title">우리 아이 지킴이</p>
      <div className="button-container">
        <button className={selectedButton === 'button1' ? 'bold' : ''} onClick={() => handleButtonClick('button1')}>대시보드</button>
        <button className={selectedButton === 'button2' ? 'bold' : ''} onClick={() => handleButtonClick('button2')}>상세</button>
        <button className={selectedButton === 'button3' ? 'bold' : ''} onClick={() => handleButtonClick('button3')}>변화</button>
      </div>
      {selectedButton === 'button1' && <ClientComponent />} {/* 대시보드가 선택되었을 때만 ClientComponent를 렌더링 */}
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
