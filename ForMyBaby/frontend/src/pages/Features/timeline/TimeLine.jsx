import React, { useState, useEffect } from 'react';
import NavBar from '../../../components/NavBar';
import { useUserStore } from '../../../stores/UserStore';
import HealthContent from './HealthContent';
import VaccineContent from './VaccineContent';
import { useLocation } from "react-router-dom";
import { MdArrowBackIos } from 'react-icons/md';


const TimeLinePage = () => {
    const [selectedButton, setSelectedButton] = useState('button1');
    const { babySelected, setBabySelected } = useUserStore();
    const loc = useLocation();
    const params = new URLSearchParams(loc.search);
    var babyId = params.get('babyId');
    var type = params.get('type');

    // type에 따라 기본 선택된 버튼 설정
    useEffect(() => {
        if (babyId != null && babyId != '' && babyId !== babySelected){
            setBabySelected(babyId);
        }
        if (type != null && type != ''){
            if (type === 'vaccine') {
                setSelectedButton('button2');
            } else {
                setSelectedButton('button1');
            }
        }
    }, [type]);

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