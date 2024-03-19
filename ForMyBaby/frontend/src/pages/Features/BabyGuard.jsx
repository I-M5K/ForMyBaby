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
      <h1 className="title">우리 아이 지킴이</h1>
      <div className="button-container">
        <button className={selectedButton === 'button1' ? 'bold' : ''} onClick={() => handleButtonClick('button1')}>Button 1</button>
        <button className={selectedButton === 'button2' ? 'bold' : ''} onClick={() => handleButtonClick('button2')}>Button 2</button>
        <button className={selectedButton === 'button3' ? 'bold' : ''} onClick={() => handleButtonClick('button3')}>Button 3</button>
      </div>
      <div className="cctv-container">
        {/* Placeholder for CCTV */}
        <p>CCTV 화면</p>
      </div>
      <div className="sleep-info">
        <h2>수면 정보</h2>
        {/* Display sleep status */}
        <p>움직임: {sleepStatus.movement}%</p>
        <p>깨어남: {sleepStatus.awake}%</p>
        <p>위험: {sleepStatus.danger}%</p>
        <p>온도: {sleepStatus.temperature}°C</p>
        <p>습도: {sleepStatus.humidity}%</p>
      </div>
      <div className="sleep-chart">
        <h2>오늘의 수면 상황</h2>
        {/* Placeholder for sleep charts */}
        <div className="chart">
          <p>움직임</p>
          {/* Placeholder for movement chart */}
        </div>
        <div className="chart">
          <p>깨어남</p>
          {/* Placeholder for awake chart */}
        </div>
        <div className="chart">
          <p>위험</p>
          {/* Placeholder for danger chart */}
        </div>
        <div className="chart">
          <p>온도</p>
          {/* Placeholder for temperature chart */}
        </div>
        <div className="chart">
          <p>습도</p>
          {/* Placeholder for humidity chart */}
        </div>
      </div>
      <NavBar />
    </div>
  );
};

export default App;
