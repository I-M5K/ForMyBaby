import React from 'react';
import './notificationBox.css';

const NotificationBox = ({ type, content, time, onClick }) => {
  return (
    <div className="notification-box" onClick={onClick}>
      {/* 알림 종류 */}
      <div className="notification-type">
        {type}
      </div>
      
      {/* 알림 내용 */}
      <div className="notification-content">{content}</div>

      {/* 알림 시간 */}
      <div className="notification-time">{time}</div>
    </div>
  );
}

export default NotificationBox;
