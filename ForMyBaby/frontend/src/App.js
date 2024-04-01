import React, { useState, useEffect } from 'react';
//import AppRouter from './Router'
import './App.css'
import Tutorial from './components/tutorial/Tutorial'
import Login from './pages/StartPage/Login'
import Loading from './pages/StartPage/LoadingPage'
import WebSocketComponent from './Socket/WebSocketComponent'; 
import { BrowserRouter, Navigate } from 'react-router-dom';
import { Routes, Route } from 'react-router-dom';
import "./FCM/firebase-messaging-sw.js";

import FamilyCode from './pages/StartPage/FamilyCode'
import KakaoRedirectPage from './pages/StartPage/KakaoRedirectPage';
import AgreePage from './pages/StartPage/AgreePage';
import MainPage from './pages/MainPage';
import WelcomePage from './pages/StartPage/WelcomePage';
import NotificationPage from './pages/Notification/NotificationPage';
import NotificationSetting from './pages/Notification/NotificationSetting';

// 아이 등록
import BabyAdd from './pages/BabyAdd/BabyAdd';
import BabyAddMore from './pages/BabyAdd/BabyAddMore'
import BabyRelation from './pages/BabyAdd/BabyRelation'
import BabyWelcome from './pages/BabyAdd/BabyWelcome'

// 아이 지킴이
import BabyGuard from './pages/Features/BabyGuard';
import BabyGuardDetail from './pages/Features/BabyGuardDetail';
// 건강/접종 타임라인
import TimeLine from './pages/Features/timeline/TimeLine';
// 성장 스탬프
import ParentingStamp from './pages/ParentingStamp/ParentingStamp';
// 스톱모션
import Present from './pages/Features/Present';
// 마이 페이지
import MyPage from './pages/MyPage/MyPage';
import BabyProfileEdit from './pages/MyPage/BabyProfileEdit';

import TutorialAndLogin from './components/TutorialAndLogin'


function App() {
  const [userLoggedIn, setUserLoggedIn] = useState(false);
  const socketUrl = 'http://j10c202.p.ssafy.io:8083';
  //const socketUrl = 'http://localhost:3001';
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
        {/* 시작 페이지 */}
          {/* <Route path="/main" element={<MainPage />} /> */}
          <Route path="/" element={<TutorialAndLogin />} />
          <Route path="/oauth/redirected/kakao" element={<KakaoRedirectPage />} />
          <Route path="/agree" element={<AgreePage />} />
          <Route path="/welcome" element={<WelcomePage />} />
          <Route path="/baby-add" element={<BabyAdd />} />
          <Route path="/baby-add-more" element={<BabyAddMore />} />
          <Route path="/baby-relation" element={<BabyRelation />} />
          <Route path="/baby-welcome" element={<BabyWelcome />} />
          <Route path="/family" element={<FamilyCode />} />
          <Route path="/baby-guard" element={<BabyGuard />} />
          <Route path="/loading" element={<Loading />} />
          <Route path="/baby-profile" element={<BabyProfileEdit />} />
          <Route
            path="/main"
            element={
              <div>
                <WebSocketComponent endpoint={socketUrl} />
                <MainPage />
              </div>
            }
          />
          <Route
            path="/baby-detail"
            element={
              <div>
                <WebSocketComponent endpoint={socketUrl} />
                <BabyGuardDetail />
              </div>
            }
          />
          <Route
            path="/timeline"
            element={
              <div>
                <WebSocketComponent endpoint={socketUrl} />
                <TimeLine />
              </div>
            }
          />
          <Route
            path="/parenting-stamp"
            element={
              <div>
                <WebSocketComponent endpoint={socketUrl} />
                <ParentingStamp />
              </div>
            }
          />
          <Route
            path="/present"
            element={
              <div>
                <WebSocketComponent endpoint={socketUrl} />
                <Present />
              </div>
            }
          />
          <Route
            path="/my-page"
            element={
              <div>
                <WebSocketComponent endpoint={socketUrl} />
                <MyPage />
              </div>
            }
          />
          <Route
            path="/notification"
            element={
              <div>
                <WebSocketComponent endpoint={socketUrl} />
                <NotificationPage />
              </div>
            }
          />
          <Route
            path="/notification/setting"
            element={
              <div>
                <WebSocketComponent endpoint={socketUrl} />
                <NotificationSetting />
              </div>
            }
          />
        </Routes>
      </BrowserRouter>
    </div>
  );
}


export default App;
