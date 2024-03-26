import React, { useState } from 'react';
import StampMap from '../../../assets/paren_stamp_map.png';
import MoonBaby from '../../../assets/presentbaby.png'
import InfoModal from './InfoModal'
import './StampPage.css'; 

const AgeGroup4To6Months = () => {
  const [isModalOpen, setIsModalOpen] = useState(false);

  const openModal = () => setIsModalOpen(true);
  const closeModal = () => setIsModalOpen(false);

  return (
    <div className="age-group-container">
      <div className="image-container">
        <img src={StampMap} alt="Stamp Map" className="stamp-map" />
        <button className="stamp-button" style={{ top: '5.5%', left: '47.5%' }} onClick={openModal}>
          <img src={MoonBaby} alt="" />
        </button>
        <button className="stamp-button" style={{ top: '22.5%', left: '8%' }} onClick={openModal}>
          <img src={MoonBaby} alt="" />
        </button>
        <button className="stamp-button" style={{ top: '32%', left: '62.5%' }} onClick={openModal}>
          <img src={MoonBaby} alt="" />
        </button>
        <button className="stamp-button" style={{ top: '65.5%', left: '70%' }} onClick={openModal}>
          <img src={MoonBaby} alt="" />
        </button>
        <button className="stamp-button" style={{ top: '72%', left: '16%' }} onClick={openModal}>
          <img src={MoonBaby} alt="" />
        </button>
      </div>
      <InfoModal isOpen={isModalOpen} onClose={closeModal}>
        <h2>정보 입력</h2>
        <p>여기에 필요한 정보 입력 폼을 추가하세요.</p>
      </InfoModal>
    </div>
  );
};

export default AgeGroup4To6Months;
