import React from 'react';
import './notificationBox.css';

const NotificationBox = ({ type, content, time, onDelete }) => {

  const handleSwipeDelete = () => {
    onDelete(); // 부모 컴포넌트에서 전달받은 삭제 함수 실행
  }

  const typeClass = type === 'info' ? 'info' : type === 'warning' ? 'warning' : ''

  return (
    <div className={`notification-box ${typeClass}`} onClick={handleSwipeDelete}> 
      <div className='noti-container'>
        <div className="notification-content">{content}</div>
        <div className="notification-time">{time}</div>
        <button className="delete-notification-button" onClick={onDelete}>삭제</button>
      </div>
    </div>
  );
}

export default NotificationBox;
