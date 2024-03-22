import React, { useState, useEffect } from 'react';
import './NotificationToggle.css';
import { sendNotificationSetting, getNotificationSetting } from '../../api/notificationApi';
import { Link, useNavigate } from 'react-router-dom'; 

const NotificationSettings = () => {
  const [notificationStates, setNotificationStates] = useState({
    sound: false,
    health: false,
    danger: false
  });
  const navigate = useNavigate(); 

  useEffect(() => {
    const fetchInitialNotificationStates = async () => {
      try {
        const initialStatesFromServer = await getNotificationSetting();
        setNotificationStates(initialStatesFromServer);
      } catch (error) {
        console.error('알림 설정 가져오기 에러!', error);
      }
    };

    fetchInitialNotificationStates();
  }, []);

  const toggleNotification = (type) => {
    const updatedStates = {
      ...notificationStates,
      [type]: !notificationStates[type]
    };
    setNotificationStates(updatedStates);
  };

  const handleSave = async () => {
    try {
      await sendNotificationSetting(notificationStates);
      console.log('알림 설정이 성공적으로 변경되었습니다.');
      navigate(-2);
    } catch (error) {
      console.error('알림 설정 전송 에러!', error);
    }
  };

  return (
    <div>
      <Link to="/main">
        <button className="settings-button" onClick={handleSave}>뒤로가기</button>
      </Link>
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
        <input type="checkbox" checked={notificationStates.danger} onChange={() => toggleNotification('danger')} />
        <span className="slider round"></span>
      </label>
    </div>
  );
}

export default NotificationSettings;
