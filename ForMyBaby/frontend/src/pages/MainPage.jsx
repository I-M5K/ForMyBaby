import React, { useEffect } from 'react';
import { Link } from 'react-router-dom'; // Link 컴포넌트 import
import NavBar from '../components/NavBar';
import './MainPage.css';
import useGeoLocation from "../hooks/useGeolocation";
import { sendLocation } from '../api/userApi';
import { requestPermission } from "../FCM/firebase-messaging-sw";
import { useUserStore } from '../stores/UserStore'; // Zustand 스토어 import

const MainPage = () => {
  const location = useGeoLocation();
  const { id, name, email, fcm, setFcm } = useUserStore();

  useEffect(() => {
    const fetchData = async () => {
      if (fcm == null){
        requestPermission();
        setFcm(localStorage.getItem('fcmToken'));
        localStorage.removeItem('fcmToken');
      }
      if (location && location.loaded && location.coordinates) {
        await sendLocation(location.coordinates.lat, location.coordinates.lng);
      }
    };
    fetchData();
  }, [location]);

  return (
    <div className="container">
      <div className="header">
        <span className="headerText">
          <>
            지금은 무준이가<br />
            낮잠 잘 시간이에요!
          </>
        </span>
        <Link to="/notification">
          <div className="notificationIcon">
            <img src={require('../assets/mdi_bell.png')} alt="Notification Bell"/>
          </div>
        </Link>
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
            <span className="boxText">a</span>
          </div>
        </Link>
        <Link to="/sleep-pattern">
          <div className="smallBox">
            <>
              <span className="boxText">
                우리아이<br />
                수면패턴
              </span>
            </>
          </div>
        </Link>
      </div>
      
      <div className="boxContainerLeft">
        <Link to="/baby-age">
          <div className="smallBox">
            <>
              <span className="boxText">
                무준이가 태어난지<br />
                27일 되었어요
              </span>
            </>
          </div>
        </Link>
        <Link to="/timeline">
          <div className="smallBox">
            <>
              <span className="boxText">
                이번주<br />
                건강검진
              </span>
            </>
          </div>
        </Link>
        <Link to="/parenting-tips">
          <div className="smallBox">
            <>
              <span className="boxText">
                우리아이<br />
                육아꿀팁
              </span>
            </>
          </div>
        </Link>
      </div>

      <NavBar />
    </div>
  );
};

export default MainPage;
