import React from 'react';
import './BabyProfileEdit.css';
import babyImage from '../../assets/hagrid.png'; // 아이 사진 이미지 경로를 적절히 수정해주세요.

import { Link } from 'react-router-dom'; // Link 컴포넌트 import
import Xbutton from '../../assets/x_button.png'

const KidInfo = () => {
    return (
        <div className="baby-profile-container">
            <Link to="/main">
                <button className="present-quit-button">
                    <img src={Xbutton} className='present-quit-button-img' />
                </button>
            </Link>
        <div className="baby-profile">
            <div className="baby-profile-image">
            <img src={babyImage} alt="baby" />
            </div>
            <div className="baby-info">
            <div className="baby-info-item">
                <span className="baby-label">성별</span>
                <div className="baby-gender">
                    <div className="gender-option">남</div>
                    <div className="gender-option">여</div>
                </div>
            </div>
            <div className="baby-info-item">
                <span className="baby-label">이름</span>
                <span className="baby-value">땡구</span>
            </div>
            <div className="baby-info-item">
                <span className="baby-label">생일</span>
                <span className="baby-value">2020년 5월 15일</span>
            </div>
            </div>
        </div>
        </div>
    );
};

export default KidInfo;
