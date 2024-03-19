import React from 'react';
import ReactDOM from 'react-dom/client';
//import AppRouter from './Router'
import './App.css'
import Tutorial from './components/tutorial/Tutorial'
import Login from './pages/StartPage/Login'

import { BrowserRouter } from 'react-router-dom';
import { Routes, Route } from 'react-router-dom';

import FamilyCode from './pages/StartPage/FamilyCode'
import KakaoRedirectPage from './pages/StartPage/KakaoRedirectPage';
import AgreePage from './pages/StartPage/AgreePage';
import MainPage from './pages/MainPage';
import WelcomePage from './pages/StartPage/WelcomePage';
import NotificationPage from './pages/Notification/NotificationPage';

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

function App() {
  return (
    <div className='App'>
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<TutorialAndLogin />} />
          <Route path="/oauth/redirected/kakao" element={<KakaoRedirectPage />}></Route>
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
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
