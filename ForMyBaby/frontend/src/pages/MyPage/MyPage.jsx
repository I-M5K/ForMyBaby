import React from 'react'
import Profile from '../../components/mypage/Profile'
import ChildInfo from '../../components/mypage/ChildInfo'
import FamilyInfo from '../../components/mypage/FamilyInfo'
import SettingsTab from '../../components/mypage/SettingTab'
import TutorialAndLogin from '../../components/TutorialAndLogin'

import './MyPage.css'

import NavBar from '../../components/NavBar';

import { LuLogOut } from "react-icons/lu";

import { Link } from 'react-router-dom';

const MyPage = () => {

    const handleLogout = () => {
        localStorage.clear();
      };

    return (
        <div className='mypage'>
            <Profile />
            <br />
            <ChildInfo />
            <FamilyInfo/>
            <SettingsTab />
            <Link to="/">
                <div onClick={() => handleLogout()} className='logout-btn'><LuLogOut /></div>
            </Link>
            <NavBar />
        </div>
    );
}

export default MyPage;