import React, { useState, useEffect } from 'react';
import socketIOClient from 'socket.io-client';
import { useUserStore } from '../stores/UserStore';
import { getMotionCnt, sendMotionUrl } from '../api/stopmotionApi';
import { createStampByAI } from '../api/stampApi';
import { sendDanger, sendAwake, sendSleep } from '../api/sleepApi';
import { useRecordStore } from '../stores/RecordStore';
import { getTodayData } from "../api/sleepApi";

const WebSocketComponent = ({ endpoint }) => {
  const [socket, setSocket] = useState(null);
  const { babySelected,stopCnt, setStopCnt } = useUserStore();
  const { danger, hours, awake, setDanger, setHours, setAwake, sleep, setSleep } = useRecordStore();

  const status = false;
  useEffect(() => {
    const newSocket = socketIOClient(endpoint, {
      transports: ['websocket'], // WebSocket 프로토콜 강제 사용
    });
    setSocket(newSocket);

    return () => {
      newSocket.disconnect(); // 컴포넌트가 언마운트될 때 소켓 연결 종료
    };
  }, [endpoint]); // endpoint가 변경될 때마다 연결 생성 또는 재연결

  useEffect(() => {
    if (socket) {
      // 연결이 설정되면 babyId를 소켓 서버로 보냅니다.
      //const babyId = 1; // 보내고자 하는 babyId 값
      socket.emit('babyId', babySelected);
      socket.emit('status', status);
      console.log('소켓통신: babyId 송신', babySelected);
      console.log('소켓통신: status 송신', status);
    }
    if (socket && socket.on) {
      // 소켓 일반 스탬프용 이벤트 수신
      socket.on('commonEvent', (data) => {
        // commonEvent 이벤트를 수신했을 때 할 작업을 여기에 작성합니다.
        console.log('Received commonEvent - 스톱모션:', data);
        const detail = data.detail;
        if (detail == '0'){ // 스톱모션
          //const response = getMotionCnt();
          // 스톱모션 사진 수 저장하기
          if (stopCnt){
            setStopCnt(stopCnt+1);
            sendMotionUrl(data.url_s3);
          }
        } else if (detail == '1'){ // 이벤트-위치정보
          console.log('Received commonEvent - 위치정보:', data);
        } else { // 성장 스탬프 - 만세 or 다리 꼬기
          console.log('Received commonEvent - 성장스탬프:', data);
          createStampByAI({ babyId: data.baby_id, step: detail, stampUrl: data.s3_url, memo: null })
        } 
      });

      // 소켓 위험 알림용 이벤트 수신
      socket.on('dangerEvent', (data) => {
        console.log('Received dangerEvent:', data);
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
            setAwake(awake+1);
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
    }


  }, [socket]); // socket이 변경될 때마다 실행됩니다.

  return null; // 실제로 UI를 렌더링하지 않음
};

export default WebSocketComponent;
