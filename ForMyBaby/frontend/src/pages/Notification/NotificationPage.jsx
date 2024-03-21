import React, { useEffect } from 'react';
import NotificationBox from '../../components/notification/NotificationBox';
import { useNotificationStore } from '../../stores/NotificationStore'; // Zustand 스토어 import
import { Link } from 'react-router-dom'; // Link 컴포넌트 import

const NotificationPage = () => {
  const { notifications, fetchNotificationList, deleteNotificationById, deleteAllNotifications } = useNotificationStore();

  useEffect(() => {
    fetchNotificationList(); // 페이지가 처음 렌더링될 때 알림 데이터를 가져옴
  }, []); // 빈 배열을 전달하여 이펙트가 한 번만 실행되도록 함

  const handleDeleteNotification = async (notificationId) => {
    await deleteNotificationById(notificationId); // 알림 삭제
  };

  const handleDeleteAllNotifications = async () => {
    await deleteAllNotifications(); // 모든 알림 삭제
  };

  return (
    <div className="notification-page">
      <div className="notification-header">
        <h1>알림</h1>
        <div className="notification-buttons">
          <button className="delete-all-button" onClick={handleDeleteAllNotifications}>전체 삭제</button>
          <Link to="/notification/setting">
            <button className="settings-button">설정</button>
          </Link>
        </div>
      </div>
      <div className="notification-list">
        {notifications.map(notification => (
          <NotificationBox
            key={notification.notificationId}
            type={notification.notificationType}
            content={notification.content}
            time={notification.createdAt}
            onDelete={() => handleDeleteNotification(notification.notificationId)}
          />
        ))}
      </div>
    </div>
  );
}

export default NotificationPage;
