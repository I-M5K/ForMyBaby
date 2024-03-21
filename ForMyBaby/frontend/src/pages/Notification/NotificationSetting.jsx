import React, { useState, useEffect } from 'react';
import './NotificationToggle.css';
import { sendNotificationSetting, getNotificationSetting } from '../../api/notificationApi';

function NotificationSettings() {
  // 각 알림 상태를 관리하는 상태 변수
  const [notificationStates, setNotificationStates] = useState({
    sound: false,
    health: false,
    danger: false
  });


  // 페이지가 마운트될 때 초기 상태를 서버에서 가져오는 함수
  useEffect(() => {
    const fetchInitialNotificationStates = async () => {
      try {
        // 서버에서 초기 알림 상태를 가져오기
        const initialStatesFromServer = await getNotificationSetting();
        setNotificationStates(initialStatesFromServer);
      } catch (error) {
        console.error('알림 설정 가져오기 에러!', error);
      }
    };

    fetchInitialNotificationStates(); // 함수 호출
  }, []);

  const initialStates = { ...notificationStates };

  // 알림 상태를 토글하는 함수
  const toggleNotification = (type) => {
    setNotificationStates(prevState => ({
      ...prevState,
      [type]: !prevState[type] // 해당 타입의 상태를 반전시킴
    }));
  };

  // 페이지를 나갈 때 변경된 상태만 서버에 전송하는 함수
  const sendChangedStatesToServer = async () => {

    const changedStates = {};

    // 변경된 상태를 확인하여 배열에 추가
    if (notificationStates.sound !== initialStates.sound) {
        changedStates['sound'] = notificationStates.sound;
    }
    if (notificationStates.health !== initialStates.health) {
        changedStates['health'] = notificationStates.health;
    }
    if (notificationStates.danger !== initialStates.danger) {
        changedStates['danger'] = notificationStates.danger;
    }

    console.log('바뀐 type', changedStates);
    if (changedStates.length > 0){ // 바뀐게 있을 때만 서버로 보내기
        await sendNotificationSetting(changedStates);
    }
  };

  // 페이지를 나갈 때만 변경된 상태를 확인하여 서버에 전송
  useEffect(() => {
    return () => {
      sendChangedStatesToServer();
    };
  }, []); // 의존성 배열이 비어 있으므로 컴포넌트가 마운트 해제될 때만 호출됨

  return (
    <div>
      <h1>알림 설정</h1>
      <p>알림 소리</p>
      <label className="switch">
        <input type="checkbox" checked={notificationStates.sound} onChange={() => toggleNotification('sound')} />
        <span className="slider round"></span>
      </label>

      <p>건강/접종 알림</p>
      <label className="switch">
        <input type="checkbox" checked={notificationStates.health} onChange={() => toggleNotification('health')} />
        <span className="slider round"></span>
      </label>

      <p>위험 예방 알림</p>
      <label className="switch">
        <input type="checkbox" checked={notificationStates.prevention} onChange={() => toggleNotification('danger')} />
        <span className="slider round"></span>
      </label>
    </div>
  );
}

export default NotificationSettings;
