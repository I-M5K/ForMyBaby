import React, { useState, useEffect } from 'react';
import ReactDOM from 'react-dom/client';
//import AppRouter from './Router'
import './App.css'
import Tutorial from './components/tutorial/Tutorial'
import Login from './pages/StartPage/Login'

import { BrowserRouter, Navigate } from 'react-router-dom';
import { Routes, Route } from 'react-router-dom';

import FamilyCode from './pages/StartPage/FamilyCode'
import KakaoRedirectPage from './pages/StartPage/KakaoRedirectPage';
import AgreePage from './pages/StartPage/AgreePage';
import MainPage from './pages/MainPage';
import WelcomePage from './pages/StartPage/WelcomePage';
import NotificationPage from './pages/Notification/NotificationPage';
import NotificationSetting from './pages/Notification/NotificationSetting';

import BabyAdd from './pages/BabyAdd/BabyAdd';

// 아이 지킴이
import BabyGuard from './pages/Features/BabyGuard';
// 건강/접종 타임라인
import TimeLine from './pages/Features/timeline/TimeLine';
// 성장 스탬프
import ParentingStamp from './pages/Features/ParentingStamp';
// 스톱모션
import Present from './pages/Features/Present';
// 마이 페이지
import MyPage from './pages/MyPage/MyPage';

import TutorialAndLogin from './components/TutorialAndLogin'
import VideoPage from './VideoPage'

function App() {
  const [userLoggedIn, setUserLoggedIn] = useState(false);

  useEffect(() => {
    // 여기서 유저 정보를 확인하고 로그인 상태를 변경합니다.
    // 예를 들어, 로컬 스토리지에서 유저 정보를 가져온다고 가정합니다.
    const userInfo = localStorage.getItem('user');
    if (userInfo && userInfo.id !== 0) { // userInfo가 null이 아니고 id가 0이 아닌 경우에 로그인 상태로 설정
      setUserLoggedIn(true);
    } else {
      setUserLoggedIn(false);
    }
  }, []);

  return (
    <div className='App'>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<TutorialAndLogin />} />
          <Route path="/oauth/redirected/kakao" element={<KakaoRedirectPage />} />
          <Route path="/agree" element={<AgreePage />} />
          <Route path="/main" element={<MainPage />} />
          <Route path="/welcome" element={<WelcomePage />} />
          <Route path="/baby-add" element={<BabyAdd />} />
          <Route path="/baby-guard" element={<BabyGuard />} />
          <Route path="/timeline" element={<TimeLine />} />
          <Route path="/parenting-stamp" element={<ParentingStamp />} />
          <Route path="/present" element={<Present />} />
          <Route path="/my-page" element={<MyPage />} />
          <Route path="/family" element={<FamilyCode />} />
          <Route path="/notification" element={<NotificationPage />} />
          <Route path="/notification/setting" element={<NotificationSetting />} />
        </Routes>
      </BrowserRouter>
    </div>
  );
}


export default App;
