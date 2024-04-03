import React, { useState } from 'react';
import StampMap from '../../../assets/paren_stamp_map.png';
import MoonBaby from '../../../assets/moonbaby.png'
import InfoModal from './InfoModal'
import './StampPage.css'; 

const AgeGroup0To3Months = ({ stampList }) => {

  const [isModalOpen, setIsModalOpen] = useState(false);
  const [selectedStamp, setSelectedStamp] = useState(null);

  const openModal = (stamp) => {
    setSelectedStamp(stamp);
    setIsModalOpen(true);
  };

  const closeModal = () => {
    setSelectedStamp(null);
    setIsModalOpen(false);
  };

  if (!stampList) {
    return ;
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
        {selectedStamp && (
          <div>
            <img src = {selectedStamp.stampImage}></img>
            <p>스탬프 번호: {selectedStamp.step}</p>
            <p>스탬프 메모: {selectedStamp.memo}</p>
            <p>스탬프 등록일자: {selectedStamp.createdAt}</p>
            {/* 다른 스탬프 정보를 표시하는 방법에 따라 아래와 같이 표시할 수 있습니다. */}
            {/* <p>스탬프 이름: {selectedStamp.name}</p> */}
            {/* <p>스탬프 설명: {selectedStamp.description}</p> */}
          </div>
        )}
      </InfoModal>
    </div>
  );
};

export default AgeGroup0To3Months;
