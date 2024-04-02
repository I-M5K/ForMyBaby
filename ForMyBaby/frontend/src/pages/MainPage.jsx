import React, { useEffect, useState } from "react";
import { Link } from "react-router-dom";
import NavBar from "../components/NavBar";
import "./MainPage.css";
import useGeoLocation from "../hooks/useGeolocation";
import { sendLocation, selectBaby } from "../api/userApi";
import { getNotificationList } from "../api/notificationApi";
import { requestPermission } from "../FCM/firebase-messaging-sw";
import { useUserStore } from "../stores/UserStore";
import { getPostWord } from "../components/postWords.js";
import ChildSelect from "../components/babyselect/babyselect.jsx";

import BabyPhoto from "../assets/child_sleep.jpg";
import Books from "../assets/books.png";
import SleepChart from "../assets/sleepChart.png";
import Syringe from "../assets/syringe.png";
import PresentBox from "../assets/presentBox.png";
import { useLocation } from "react-router-dom";
import { GoBell } from "react-icons/go";

import GaugeBar from "../components/feature/present/CountBar.jsx"

const images = [
  require("../assets/bears/hogogok.png"),
  require("../assets/bears/jashinmanman.png"),
  require("../assets/bears/kingbatne.png"),
  require("../assets/bears/saranghaeyo.png"),
  require("../assets/bears/yap.png"),
]


const MainPage = () => {
  const loc = useLocation();
  const params = new URLSearchParams(loc.search);
  const babyId = params.get("babyId");

  const location = useGeoLocation();
  const {
    babyList,
    fcm,
    setFcm,
    uncheckedCnt,
    setUncheckedCnt,
    babySelected,
    setBabySelected,
  } = useUserStore();

  const [selectedBabyName, setSelectedBabyName] = useState("");
  const [selectedBabyDay, setSelectedBabyDay] = useState("");

  useEffect(() => {
    const fetchData = async () => {
      if (babySelected == null || babySelected == 0) {
        setBabySelected(babyList[0].babyId);
      }

      if (babyId) {
        console.log(babyId);
        if (babySelected != babyId) {
          setBabySelected(babyId);
          console.log("선택 아이 정보 바꾸기!");
          selectBaby(babyId);
        }
      }

      if (fcm == null) {
        requestPermission();
        setFcm(localStorage.getItem("fcmToken"));
        //localStorage.removeItem('fcmToken');
      }

      const fetchedNotifications = await getNotificationList(); // 알림 목록 가져오는 API 호출

      // check 칼럼이 false인 알림의 개수 세기
      const uncheckedNoti = fetchedNotifications.filter(
        (notification) => !notification.isChecked
      ).length;

      // 확인 안한 알림 수 업데이트
      setUncheckedCnt(uncheckedNoti);

      if (location && location.loaded && location.coordinates) {
        await sendLocation(location.coordinates.lat, location.coordinates.lng);
      }
    };
    fetchData();
  }, [location]);

  useEffect(() => {
    // 선택된 아이의 이름과 생일 업데이트
    const selectedBaby = babyList.find((baby) => baby.babyId === babySelected);
    if (selectedBaby) {
      const givenDateStr = selectedBaby.birthDate; // 주어진 날짜 문자열
      // 주어진 날짜 문자열을 연도, 월, 일로 분리
      const [year, month, day] = givenDateStr.split("-");
      // 분리된 연도, 월, 일을 이용하여 Date 객체 생성
      const givenDate = new Date(year, month - 1, day); // month는 0부터 시작하므로 1을 빼줌
      const today = new Date(); // 오늘 날짜 객체
      // 두 날짜 간의 차이를 밀리초로 계산
      const timeDiff = today.getTime() - givenDate.getTime();
      // 밀리초를 일로 변환하여 일수 차이 계산
      const daysDiff = Math.ceil(timeDiff / (1000 * 60 * 60 * 24));
      console.log("오늘과 주어진 날짜의 일수 차이:", daysDiff);

      // const particle = (() => {
      //   if (babyName && babyName.length > 0) {
      //     const lastChar = babyName[babyName.length - 1];
      //     if (lastChar) {
      //       return lastChar.match(/[가-힣]/) ? (lastChar.charCodeAt(0) - 0xac00) % 28 > 0 ? '이는' : '는' : '';
      //     }
      //   }
      //   return '';
      // })();
      if (selectedBaby.babyName && selectedBaby.babyName.length > 0) {
        const lastChar =
          selectedBaby.babyName[selectedBaby.babyName.length - 1];
        if (lastChar) {
          if (
            lastChar.match(/[가-힣]/) &&
            (lastChar.charCodeAt(0) - 0xac00) % 28 > 0
          ) {
            setSelectedBabyName(selectedBaby.babyName.slice(1) + "이가");
          } else {
            setSelectedBabyName(selectedBaby.babyName.slice(1) + "가");
          }
        }
      }
      //setSelectedBabyName(getPostWord(selectedBaby.babyName, '이', ''));
      setSelectedBabyDay(daysDiff);
    }
  }, [babyList, babySelected]);

  const handleLogout = () => {
    localStorage.clear();
  };

  const [showBottomSheet, setShowBottomSheet] = useState(false);
  const [showOverlay, setShowOverlay] = useState(false);

  const toggleBottomSheet = () => {
    setShowBottomSheet(!showBottomSheet);
    setShowOverlay(!showOverlay);
  };

  const handleNotificationClick = () => {
    setUncheckedCnt(0); // 알림 아이콘 클릭 시 알림 수를 0으로 설정
  };

  const [selectedImage, setSelectedImage] = useState(images[0]);

  useEffect(() => {
    const randomIndex = Math.floor(Math.random() * images.length); // 랜덤 인덱스 생성
    setSelectedImage(images[randomIndex]); // 선택된 이미지 상태 업데이트
  }, []);

  return (
    <div className="main-container">
      <div className="main-header">
        <span className="main-headerText">
          찬바람, 찬음식은 {selectedBabyName}
          <br />
          피해주는 게 좋아요!
        </span>
        <Link to="/notification">
          <div
            className="main-notificationIcon"
            onClick={handleNotificationClick}
          >
            {/* <img
              src={require("../assets/mdi_bell.png")}
              alt="Notification Bell"
            /> */}
            <GoBell className='goBell'/>
            {uncheckedCnt > 0 && ( // 읽지 않은 알림이 있을 때만 표시
              <span className="notification-count">{uncheckedCnt}</span>
            )}
          </div>
        </Link>
      </div>
      <img
        src={selectedImage}
        className="gombaImage"
        alt="Random Baby"
      />
      <Link to="/present">
        <div className="rectangleBox">
          <img src={PresentBox} className="presentbox" />
          <div className="rectangleBoxText">100% 채우면 과연 어떤 선물이?</div>
          <GaugeBar value={70} maxValue={100} />
        </div>
      </Link>

      <div className="main-content">
        <div className="boxContainerLeft">
          <div className="yellowBox" onClick={toggleBottomSheet}>
            <span className="boxText">
              <span className="textMiddle">{selectedBabyName} 태어난지</span>
              <br />
              <span className="textExSmall">
                <div className='babyday'>{selectedBabyDay}</div> 일 되었어요
              </span>
            </span>
          </div>

          <Link to="/timeline">
            <div className="smallBox">
              <span className="boxText">
                <span className="textSmall">이번주</span>
                <br />
                <span className="textLarge">건강/검진</span>
              </span>
              <img src={Syringe} className="syringe" />
            </div>
          </Link>
          <Link to="/parenting-tips">
            <div className="smallBox">
              <span className="boxText">
                <span className="textSmall">우리아이</span>
                <br />
                <span className="textLarge">육아꿀팁</span>
              </span>
              <img src={Books} className="syringe" />
            </div>
          </Link>
        </div>

        <div className="boxContainerRight">
          <Link to="/baby-profile">
            <div className="smallmiddleBox">
              {/* <img src={babyList.babySelected.profileImg} className="babyphoto" /> */}
            </div>
          </Link>

          <Link to="/baby-guard?selectedButton=button2">
            <div className="smallBox">
              <span className="boxText">
                <span className="textSmall">우리아이</span>
                <br />
                <span className="textLarge">수면패턴</span>
              </span>
              <img src={SleepChart} className="syringe" />
            </div>
          </Link>
        </div>
      </div>

      <div
        className={`bottomSheet ${showBottomSheet ? "showBottomSheet" : ""}`}
      >
        <div className="bottomSheetContent">
          <ChildSelect handleClose={toggleBottomSheet} />
        </div>
      </div>

      <div
        className={`overlay ${showOverlay ? "showOverlay" : ""}`}
        onClick={toggleBottomSheet}
      ></div>
      <NavBar />
    </div>
  );
};

export default MainPage;
