import React from 'react';
import StampMap from '../../../assets/paren_stamp_map.png';
import MoonBaby from '../../../assets/babybear.png'

import './StampPage.css'; 

const AgeGroup10To12Months = () => {
  return (
    <div className="age-group-container">
      <div className="image-container">
        <img src={StampMap} alt="Stamp Map" className="stamp-map" />
        <button className="stamp-button" style={{ top: '5.5%', left: '47.5%' }}>
          <img src={MoonBaby} alt="" />
        </button>
        <button className="stamp-button" style={{ top: '22.5%', left: '8%' }}>
          <img src={MoonBaby} alt="" />
        </button>
        <button className="stamp-button" style={{ top: '32%', left: '62.5%' }}>
          <img src={MoonBaby} alt="" />
        </button>
        <button className="stamp-button" style={{ top: '65.5%', left: '70%' }}>
          <img src={MoonBaby} alt="" />
        </button>
        <button className="stamp-button" style={{ top: '72%', left: '16%' }}>
          <img src={MoonBaby} alt="" />
        </button>
      </div>
    </div>
  );
};

export default AgeGroup10To12Months;
