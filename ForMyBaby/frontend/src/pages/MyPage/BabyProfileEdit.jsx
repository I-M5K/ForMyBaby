import React from 'react';
import './BabyProfileEdit.css';
import babyImage from '../../assets/hagrid.png'; // 아이 사진 이미지 경로를 적절히 수정해주세요.

import { Link } from 'react-router-dom'; // Link 컴포넌트 import
import Xbutton from '../../assets/x_button.png'
import { useUserStore } from "../../stores/UserStore";

const KidInfo = () => {
    const {
        babyList,
        babySelected,
      } = useUserStore();
    return (
        <div className="baby-profile-container">
            <Link to="/main">
                <button className="present-quit-button">
                    <img src={Xbutton} className='present-quit-button-img' />
                </button>
            </Link>
        <div className="baby-profile">
            <div className="baby-profile-image">
            <img src={babyList.babySelected.profileImg} alt="baby" />
            </div>
            <div className="baby-info">
            <div className="baby-info-item">
                <span className="baby-label">성별</span>
                <div className="baby-gender">
                {babyList.babySelected.babyGender === 'male' ? '남' : (babyList.babySelected.babyGender === 'female' ? '여' : null)}
                </div>
            </div>
            <div className="baby-info-item">
                <span className="baby-label">이름</span>
                <span className="baby-value">babyList.babySelected.babyName</span>
            </div>
            <div className="baby-info-item">
                <span className="baby-label">생일</span>
                <span className="baby-value">babyList.babySelected.birthDate</span>
            </div>
            </div>
        </div>
        </div>
    );
};

export default KidInfo;
