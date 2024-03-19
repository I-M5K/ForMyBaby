import React, { useState } from 'react';
import NavBar from '../../components/NavBar';

import './BabyGuard.css';

const App = () => {
  const [selectedButton, setSelectedButton] = useState(null);

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
      <div className="cctv-container">
        {/* Placeholder for CCTV */}
        <p>CCTV 화면</p>
      </div>
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
