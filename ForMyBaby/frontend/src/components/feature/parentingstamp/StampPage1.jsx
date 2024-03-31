import React, { useState } from 'react';
import StampMap from '../../../assets/paren_stamp_map.png';
import MoonBaby from '../../../assets/moonbaby.png'
import InfoModal from './InfoModal'
import './StampPage.css'; 

const AgeGroup0To3Months = ({ stampList }) => {

  const [isModalOpen, setIsModalOpen] = useState(false);

  const openModal = () => setIsModalOpen(true);
  const closeModal = () => setIsModalOpen(false);

  if (!stampList) {
    return <div>No stamp list available</div>;
  }
  const loc = [{ top: '1.5%', left: '47%' }, { top: '19.8%', left: '7%' }, { top: '30.2%', left: '62.7%' }, { top: '65%', left: '70%' }, { top: '74%', left: '23%' }]
  return (
    <div className="age-group-container">
      <div className="image-container">
        <img src={StampMap} alt="Stamp Map" className="stamp-map" />
        {
          stampList.map((stamp, index) => {
            return (
              <button
                key={index}
                className="stamp-button"
                step={stamp.step}
                style={{ top: loc[index].top, left: loc[index].left }}
                onClick={() => openModal(stamp)}
              >
                <img src={stamp.image} alt="" />
              </button>
            );
          })
        }
      </div>
      <InfoModal isOpen={isModalOpen} onClose={closeModal}>
        <h2>정보 입력</h2>
        <p>여기에 필요한 정보 입력 폼을 추가하세요.</p>
      </InfoModal>
    </div>
  );
};

export default AgeGroup0To3Months;
