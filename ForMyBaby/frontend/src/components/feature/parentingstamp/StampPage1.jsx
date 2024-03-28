import React, { useState } from 'react';
import StampMap from '../../../assets/paren_stamp_map.png';
import MoonBaby from '../../../assets/moonbaby.png'
import InfoModal from './InfoModal'
import './StampPage.css'; 

const AgeGroup0To3Months = () => {

  const [isModalOpen, setIsModalOpen] = useState(false);

  const openModal = () => setIsModalOpen(true);
  const closeModal = () => setIsModalOpen(false);

  return (
    <div className="age-group-container">
      <div className="image-container">
        <img src={StampMap} alt="Stamp Map" className="stamp-map" />
        <button className="stamp-button" style={{ top: '1.5%', left: '47%' }} onClick={openModal}>
          <img src={MoonBaby} alt="" />
        </button>
        <button className="stamp-button" style={{ top: '19.8%', left: '7%' }} onClick={openModal}>
          <img src={MoonBaby} alt="" />
        </button>
        <button className="stamp-button" style={{ top: '30.2%', left: '62.7%' }} onClick={openModal}>
          <img src={MoonBaby} alt="" />
        </button>
        <button className="stamp-button" style={{ top: '65%', left: '70%' }} onClick={openModal}>
          <img src={MoonBaby} alt="" />
        </button>
        <button className="stamp-button" style={{ top: '74%', left: '23%' }} onClick={openModal}>
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

export default AgeGroup0To3Months;
