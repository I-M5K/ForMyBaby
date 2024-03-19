import React, { useState } from 'react';
import NotificationBox from '../../components/notification/NotificationBox';

const NotificationPage = () => {
  // 가상의 알림 데이터
  const [notifications, setNotifications] = useState([
    { id: 1, type: '알림', content: '새로운 메시지가 도착했습니다.', time: '방금 전' },
    { id: 2, type: '경고', content: '잘못된 입력이 감지되었습니다.', time: '5분 전' },
    { id: 3, type: '알림', content: '오늘의 할일을 확인하세요.', time: '1시간 전' },
  ]);

  return (
    <div className="notification-page">
      <div className="notification-header">
        <h1>알림</h1>
        <div className="notification-buttons">
          {/* 전체 삭제 버튼 */}
          <button className="delete-all-button" >전체 삭제</button>
          {/* 설정 버튼 */}
          <button className="settings-button">설정</button>
        </div>
      </div>
      <div className="notification-list">
        {/* 알림 목록 렌더링 */}
        {notifications.map(notification => (
          <NotificationBox
            key={notification.id}
            type={notification.type}
            content={notification.content}
            time={notification.time}
          />
        ))}
      </div>
    </div>
  );
}

export default NotificationPage;
