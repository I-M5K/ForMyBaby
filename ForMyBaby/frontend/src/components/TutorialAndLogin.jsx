// TutorialAndLogin.js
import React, { useState, useEffect } from 'react';
import Tutorial from '../components/tutorial/Tutorial';
import Login from '../pages/StartPage/Login';
import '../components/TutorialAndLogin.css'; // 위에서 정의한 CSS 파일을 import 합니다.

const TutorialAndLogin = () => {
  const [showIntro, setShowIntro] = useState(true);

  useEffect(() => {
    // 2초 후에 인트로 효과를 비활성화합니다.
    const timer = setTimeout(() => {
      setShowIntro(false);
    }, 2000);

    // 컴포넌트가 언마운트되면 타이머를 클리어합니다.
    return () => clearTimeout(timer);
  }, []);

  return (
    <div className={showIntro ? 'animated' : ''}>
      <Tutorial />
      <Login />
      {/* <MainPage /> */}
    </div>
  );
};

export default TutorialAndLogin;
