import React from 'react'
import Profile from '../../components/mypage/Profile'
import ChildInfo from '../../components/mypage/ChildInfo'
import FamilyInfo from '../../components/mypage/FamilyInfo'
import SettingsTab from '../../components/mypage/SettingTab'

import './MyPage.css'

import NavBar from '../../components/NavBar';

const MyPage = () => {

    return (
        <div className='mypage'>
            <p className='mypage-title'>마이페이지</p>
            <Profile />
            <hr/>
            <ChildInfo />
            <hr />
            <FamilyInfo/>
            <hr />
            <SettingsTab />
            <NavBar />
        </div>
    );
}

export default MyPage;