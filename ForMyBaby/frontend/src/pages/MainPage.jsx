import React from 'react';
import NavBar from '../components/NavBar';
import './MainPage.css';

const MainPage = () => {
  return (
    <div className="container">
      <div className="header">
        <span className="headerText">
          <>
            지금은 무준이가<br />
            낮잠 잘 시간이에요!
          </>
        </span>
        <div className="notificationIcon">
          <img src={require('../assets/mdi_bell.png')} alt="Notification Bell" />
        </div>
      </div>
      <img src={require('../assets/babybear.png')} className="gombaImage" alt="Baby Bear" />
      <div className="rectangleBox" onClick={() => {}}>
        <>
          100% 채우면 과연 어떤 선물이?
        </>
      </div>

      <div className="boxAvengers">
        <div className="boxContainerRight">
          <div className="smallmiddleBox" onClick={() => {}}>
            a
          </div>
          <div className="smallBox" onClick={() => {}}>
            <>
              우리아이<br />
              수면패턴
            </>
          </div>
        </div>
        
        <div className="boxContainerLeft">
          <div className="smallBox" onClick={() => {}}>
            <>
              무준이가 태어난지<br />
              27일 되었어요
            </>
          </div>
          <div className="smallBox" onClick={() => {}}>
            <>
              이번주<br />
              건강검진
            </>
          </div>
          <div className="smallBox" onClick={() => {}}>
            <>
              우리아이<br />
              육아꿀팁
            </>
          </div>
        </div>
      </div>
      <NavBar />
    </div>
  );
};

export default MainPage;
