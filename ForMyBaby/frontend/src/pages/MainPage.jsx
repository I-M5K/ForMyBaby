import React from 'react';
import { Link } from 'react-router-dom'; // Link 컴포넌트 import
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
      <Link to="/present">
        <div className="rectangleBox">
          <>
            100% 채우면 과연 어떤 선물이?
          </>
        </div>
      </Link>

        <div className="boxContainerRight">
          <Link to="/sleep-pattern">
            <div className="smallmiddleBox">
              a
            </div>
          </Link>
          <Link to="/sleep-pattern">
            <div className="smallBox">
              <>
                우리아이<br />
                수면패턴
              </>
            </div>
          </Link>
        </div>
        
        <div className="boxContainerLeft">
          <Link to="/baby-age">
            <div className="smallBox">
              <>
                무준이가 태어난지<br />
                27일 되었어요
              </>
            </div>
          </Link>
          <Link to="/timeline">
            <div className="smallBox">
              <>
                이번주<br />
                건강검진
              </>
            </div>
          </Link>
          <Link to="/parenting-tips">
            <div className="smallBox">
              <>
                우리아이<br />
                육아꿀팁
              </>
            </div>
          </Link>
        </div>

      <NavBar />
    </div>
  );
};

export default MainPage;
