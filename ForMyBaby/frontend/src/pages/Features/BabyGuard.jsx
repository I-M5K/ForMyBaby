import React, { useState, useEffect } from "react";
import socketIOClient from "socket.io-client";
import NavBar from "../../components/NavBar";
import DetailContent from "./BabyGuardDetail";
import ChangeContent from "./BabyGuardChange";
import SleepStatusContent from "./SleepStatusContent";
import { useRecordStore } from "../../stores/RecordStore";
import "./BabyGuard.css";
import { getTodayData } from "../../api/sleepApi";
import { useUserStore } from "../../stores/UserStore";
import { useLocation } from "react-router-dom";
import { getMotionCnt, sendMotionUrl } from "../../api/stopmotionApi";
import { createStampByAI } from "../../api/stampApi";
import { sendDanger, sendAwake, sendSleep } from "../../api/sleepApi";

const ENDPOINT = 'http://localhost:8083';
// const ENDPOINT = "https://j10c202.p.ssafy.io/ai";

// const ImageContent = ({ imageData, lineData }) => (
//   <div className="image-content">
//     <h1>Received Image</h1>
//     {imageData && <img src={imageData} alt="Received" />}
//     {lineData && <p>Received Line: {lineData}</p>}
//   </div>
// );

const Dashboard = () => {
  const [imageData, setImageData] = useState(null);
  const [temp, setTemp] = useState("");
  const [humid, setHumid] = useState("");
  const [timestamp, setTimestamp] = useState("");
  const [selectedButton, setSelectedButton] = useState("button1");
  const { babySelected, stopCnt, setStopCnt } = useUserStore();
  const {
    danger,
    hours,
    awake,
    setDanger,
    setHours,
    setAwake,
    sleep,
    setSleep,
  } = useRecordStore();

  const location = useLocation();
  const queryParams = new URLSearchParams(location.search);

  // 선택된 버튼 상태를 확인합니다.
  const getButton = queryParams.get("selectedButton");

  const status = true;

  const handleButtonClick = (buttonName) => {
    setSelectedButton(buttonName);
  };

  useEffect(() => {
    const fetchData = async () => {
      if (getButton != null) {
        handleButtonClick("button2");
      }
      if (danger === null || awake === null || hours === null) {
        try {
          // API 호출하여 데이터 가져오기
          const response = await getTodayData();
          // 가져온 데이터를 상태로 업데이트
          useRecordStore.setState({
            danger: response.dangerCnt,
            awake: response.sleepCnt,
            hours: response.sleepTime,
          });
        } catch (error) {
          console.error("Error fetching data:", error);
        }
      }
    };

    fetchData();

    const socket = socketIOClient(ENDPOINT, {
      transports: ["websocket"],
    });

    socket.emit("babyId", babySelected);
    socket.emit("status", status);
    console.log("소켓통신: babyId 송신", babySelected);
    console.log("소켓통신: status 송신", status);

    socket.on("image", ({ imageData, babyId, timestamp, temp, humid }) => {
      const base64String = btoa(
        new Uint8Array(imageData).reduce(
          (data, byte) => data + String.fromCharCode(byte),
          ""
        )
      );
      setImageData(`data:image/jpeg;base64,${base64String}`);
      setTemp(temp);
      setHumid(humid);
      setTimestamp(timestamp);
      console.log("온습도 데이터", temp, humid);
      console.log("시간", timestamp);
    });

    socket.on('commonEvent', (data) => {
      // commonEvent 이벤트를 수신했을 때 할 작업을 여기에 작성합니다.
      console.log("Received commonEvent - 스톱모션:", data);
      const detail = data.detail;
      if (detail == "0") {
        // 스톱모션
        //const response = getMotionCnt();
        // 스톱모션 사진 수 저장하기
        if (stopCnt) {
          setStopCnt(stopCnt + 1);
          sendMotionUrl(data.url_s3);
        }
      } else if (detail == '1'){ // 이벤트-위치정보
        console.log('Received commonEvent - 위치정보:', data);
      } else { // 성장 스탬프 - 만세 or 다리 꼬기
        console.log('Received commonEvent - 성장스탬프:', data);
        createStampByAI({  babyId: data.baby_id, step: detail, stampUrl: data.s3_url, memo: null })
      } 
    });

    // 소켓 위험 알림용 이벤트 수신
    socket.on("dangerEvent", (data) => {
      console.log("Received dangerEvent:", data);
      const response = getTodayData();

      setDanger(response.dangerCnt+1);
      sendDanger(data.baby_id, data.detail);

      // if (danger == null){
      //   setDanger(1);
      // } else {
      //   setDanger(danger+1);
      // }
    });

    // 소켓 수면 분석용 이벤트 수신
    socket.on('sleepEvent', (data) => {
      console.log('Received sleepEvent:', data);
      if (data.detail == '0'){ // 잠에서 깸
        console.log('Received sleepEvent - 잠에서 깸', data);
        sendAwake(data.baby_id)
        if (awake == null){
          setAwake(1);
        } else {
          setAwake(awake + 1);
        }
        // // *************** 시간 설정 (어제, 오늘) 필요 *************************
        // // 잠든 시간을 가져옵니다.
        // const sleepTime = new Date(sleep).getTime();
        // // 수신한 timestamp를 밀리초로 변환합니다.
        // const awakeTime = new Date(data.timestamp).getTime();
        // // 두 timestamp 사이의 차이를 분으로 계산합니다.
        // const timeDifference = (awakeTime - sleepTime) / (1000 * 60);
        // console.log('Time difference (minutes):', timeDifference);
        // setHours(hours+timeDifference);
      } else if (data.detail == '1') { // 잠 듦
        console.log('Received sleepEvent - 잠듦', data);
        sendSleep(data.baby_id)
        //setSleep(data.timestamp);
      }
    });
    return () => {};
  }, []);

  return (
    <div className="dashboard">
      <div className="sleep-title">우리 아이 지킴이</div>
      <div className="button-container">
        <button
          className={selectedButton === "button1" ? "bold" : ""}
          onClick={() => handleButtonClick("button1")}
        >
          실시간 현황
        </button>
        <button
          className={selectedButton === "button2" ? "bold" : ""}
          onClick={() => handleButtonClick("button2")}
        >
          상세
        </button>
      </div>
      <div className="babyguard-content">
        {selectedButton === "button1" && (
          <div className="dashboard-content">
            <div className="video-content">
              {/* <h1>Real-time Video</h1> */}
              {/* 이미지가 없는 경우를 처리하여 메시지 표시 */}
              {/* {!imageData && (
                <p style={{ color: "#666" }}>No video available</p>
              )} */}
              
                <img
                  src={imageData}
                  alt="Received"
                  style={{ maxWidth: "100%", maxHeight: "100%" }}
                />
             
            </div>
            <SleepStatusContent
              danger={danger}
              hours={hours}
              awake={awake}
              temp={temp}
              humid={humid}
            />{" "}
            {/* 수정된 부분 */}
          </div>
        )}
        {selectedButton === "button2" && (
          <DetailContent danger={danger} hours={hours} awake={awake} />
        )}
        {selectedButton === "button3" && <ChangeContent />}
      </div>
      <NavBar />
    </div>
  );
};

export default Dashboard;
