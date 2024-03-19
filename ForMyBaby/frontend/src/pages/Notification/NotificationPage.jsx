import React, { useState, useEffect } from 'react';
import NotificationBox from '../../components/notification/NotificationBox';
import { getNotificationList, deleteNotification, deleteNotificationAll } from '../../api/notificationApi';

const NotificationPage = () => {
  const [notifications, setNotifications] = useState([]);

  useEffect(() => {
    // 페이지가 처음 렌더링될 때 알림 데이터를 가져옴
    async function fetchNotificationList() {
      try {
        const notificationList = await getNotificationList();
        setNotifications(notificationList);
      } catch (error) {
        console.error('알림 목록을 가져오는 중 에러 발생:', error);
      }
    }
    
    fetchNotificationList(); // 알림 데이터 가져오기
  }, []); // 빈 배열을 전달하여 이펙트가 한 번만 실행되도록 함

  // 알림 삭제 함수
  const handleDeleteNotification = async (notificationId) => {
    await deleteNotification(notificationId);
    // 알림 삭제 후 알림 목록 다시 가져와서 상태 업데이트
    const updatedNotificationList = await getNotificationList();
    setNotifications(updatedNotificationList);
  };

  // 모든 알림 삭제 함수
  const handleDeleteAllNotifications = async () => {
    await deleteNotificationAll();
    // 모든 알림 삭제 후 알림 목록을 빈 배열로 설정하여 화면 갱신
    setNotifications([]);
  };

  return (
    <div className="notification-page">
      <div className="notification-header">
        <h1>알림</h1>
        <div className="notification-buttons">
          {/* 전체 삭제 버튼 */}
          <button className="delete-all-button" onClick={handleDeleteAllNotifications}>전체 삭제</button>
          {/* 설정 버튼 */}
          <button className="settings-button">설정</button>
        </div>
      </div>
      <div className="notification-list">
        {/* 알림 목록 렌더링 */}
        {notifications.map(notification => (
          <NotificationBox
            key={notification.notificationId} // notificationId로 변경
            type={notification.notificationType} // notificationType으로 변경
            content={notification.content}
            time={notification.createdAt} // createdAt로 변경
            onDelete={() => handleDeleteNotification(notification.notificationId)} // 삭제 함수 전달
          />
        ))}
      </div>
    </div>
  );
}

export default NotificationPage;
