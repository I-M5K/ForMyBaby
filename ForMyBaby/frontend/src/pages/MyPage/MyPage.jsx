import React from 'react'
import Profile from '../../components/mypage/Profile'
import ChildInfo from '../../components/mypage/ChildInfo'
import FamilyInfo from '../../components/mypage/FamilyInfo'
import SettingsTab from '../../components/mypage/SettingTab'
import TutorialAndLogin from '../../components/TutorialAndLogin'

import './MyPage.css'

import NavBar from '../../components/NavBar';

import { Link, Navigate } from 'react-router-dom';

const MyPage = () => {

    const handleLogout = () => {
        localStorage.clear();
      };

    return (
        <div className='mypage'>
            <Profile />
            <hr/>
            <ChildInfo />
            <hr />
            <FamilyInfo/>
            <hr />
            <SettingsTab />
            <NavBar />
            <Link to="/">
                <button onClick={() => handleLogout()} className='logout-btn'>로그아웃</button>
            </Link>
        </div>
    );
}

export default MyPage;