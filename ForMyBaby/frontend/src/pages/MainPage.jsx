import React, { useEffect } from 'react';
import { Link } from 'react-router-dom'; // Link 컴포넌트 import
import NavBar from '../components/NavBar';
import './MainPage.css';
import useGeoLocation from "../hooks/useGeolocation";
import { sendLocation } from '../api/userApi';
import { requestPermission } from "../FCM/firebase-messaging-sw";
import { useUserStore } from '../stores/UserStore'; // Zustand 스토어 import

import BabyPhoto from '../assets/bangabanga.png'
import Books from '../assets/books.png'
import SleepChart from '../assets/sleepChart.png'
import Syringe from '../assets/syringe.png'
import PresentBox from '../assets/presentBox.png'

const MainPage = () => {
  const location = useGeoLocation();
  const { id, name, email, fcm, setFcm } = useUserStore();

  useEffect(() => {
    const fetchData = async () => {
      if (fcm == null){
        requestPermission();
        setFcm(localStorage.getItem('fcmToken'));
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
            지금은 무준이가<br />
            낮잠 잘 시간이에요!
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
          <span className="rectangleBoxText">
            100% 채우면 과연 어떤 선물이?
          </span>
        </div>
      </Link>

      <div className="boxContainerRight">
        <Link to="/my-page">
          <div className="smallmiddleBox">
            <img src={BabyPhoto} className='babyphoto' />
          </div>
        </Link>
        <Link to="/sleep-pattern">
          <div className="smallBox">
              <span className="boxText">
                <span className="textSmall">우리아이</span><br />
                <span className="textLarge">수면패턴</span>
              </span>
                <img src={SleepChart} className='syringe' />
          </div>
        </Link>
      </div>
      
      <div className="boxContainerLeft">
        <Link to="/baby-age">
          <div className="smallBox">
              <span className="boxText">
                  <span className="textLarge">무준이가 태어난지</span><br />
                  <span className="textExSmall">
                    '72'
                    일 되었어요
                  </span>
              </span>
          </div>
        </Link>
        <Link to="/timeline">
          <div className="smallBox">
              <span className="boxText">
                <span className="textSmall">이번주</span><br />
                <span className="textLarge">건강/검진</span>
              </span>
                <img src={Syringe} className='syringe' />
          </div>
        </Link>
        <Link to="/parenting-tips">
          <div className="smallBox">
              <span className="boxText">
                <span className="textSmall">우리아이</span><br />
                <span className="textLarge">육아꿀팁</span>
              </span>
                <img src={Books} className='syringe' />
          </div>
        </Link>
      </div>

      <NavBar />
    </div>
  );
};

export default MainPage;
