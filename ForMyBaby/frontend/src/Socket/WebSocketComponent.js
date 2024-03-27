import React, { useState, useEffect } from 'react';
import socketIOClient from 'socket.io-client';

const WebSocketComponent = ({ endpoint }) => {
  const [socket, setSocket] = useState(null);

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
      const babyId = 1; // 보내고자 하는 babyId 값
      socket.emit('babyId', babyId);
      console.log('소켓통신: babyId 송신', babyId);
    }
    if (socket && socket.on) {
      // 소켓 일반 스탬프용 이벤트 수신
      socket.on('commonEvent', (data) => {
        // commonEvent 이벤트를 수신했을 때 할 작업을 여기에 작성합니다.
        console.log('Received commonEvent:', data);
      });

      // 소켓 위험 알림용 이벤트 수신
      socket.on('dangerEvent', (data) => {
        // commonEvent 이벤트를 수신했을 때 할 작업을 여기에 작성합니다.
        console.log('Received commonEvent:', data);
      });

      // 소켓 수면 분석용 이벤트 수신
      socket.on('sleepEvent', (data) => {
        // commonEvent 이벤트를 수신했을 때 할 작업을 여기에 작성합니다.
        console.log('Received commonEvent:', data);
      });
    }


  }, [socket]); // socket이 변경될 때마다 실행됩니다.

  return null; // 실제로 UI를 렌더링하지 않음
};

export default WebSocketComponent;
