import React from 'react';
import { LuLogOut } from "react-icons/lu";
import { Link } from 'react-router-dom';
import arrowLeft from '../../assets/arrow_left.png'
import './MyPageSettings.css';

const MyPageSettings = () => {
    const handleLogout = () => {
        localStorage.clear();
    };

    return (
        <div className="mypage-settings-wrapper">
            <div className="mypage-settings-header">
                <Link to="/my-page">
                    <button className="mypage-settings-quit-button">
                        <img src={arrowLeft} alt="Settings" />
                    </button>
                </Link>
                <div className="settings-title">환경설정</div>
            </div>
            <div className="mypage-settings-container">
                <div>로그아웃</div>
                <Link to="/">
                    <div onClick={() => handleLogout()} className='logout-btn'><LuLogOut className='react-icon' /></div>
                </Link>
            </div>
        </div>
    );
}

export default MyPageSettings;
