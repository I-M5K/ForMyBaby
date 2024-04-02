import React, { useState, useEffect } from 'react';
import NavBar from '../../../components/NavBar';
import { useUserStore } from '../../../stores/UserStore';
import HealthContent from './HealthContent';
import VaccineContent from './VaccineContent';
import { MdArrowBackIos } from 'react-icons/md';




const TimeLinePage = () => {
    const [selectedButton, setSelectedButton] = useState('button1');
    const { babySelected } = useUserStore();
    

    const handleButtonClick = (buttonName) => {
        setSelectedButton(buttonName);
      };
    return (
        <>
        <div>
        <MdArrowBackIos className="arrow-back-icon" />
            <h2>타임 라인</h2>
            <div className="button-container" style={{marginBottom:10}}>
                <button className={selectedButton === 'button1' ? 'bold' : ''} onClick={() => handleButtonClick('button1')}>영유아 검진</button>
                <button className={selectedButton === 'button2' ? 'bold' : ''} onClick={() => handleButtonClick('button2')}>예방 접종</button>
            </div>
            {selectedButton === 'button1' ? <HealthContent /> : <VaccineContent />}
        </div>
        <div>
            <NavBar />
        </div>
        </>
    );
}

export default TimeLinePage;