import React, { useEffect, useState } from 'react';
import NotificationBox from '../../components/notification/NotificationBox';
import { useNotificationStore } from '../../stores/NotificationStore'; // Zustand 스토어 import
import { useUserStore } from '../../stores/UserStore';
import { Link } from 'react-router-dom'; // Link 컴포넌트 import
import arrowLeft from '../../assets/arrow_left.png'
import { checkNotificationAll } from '../../api/notificationApi';
import './NotificationPage.css'

const NotificationPage = () => {
 const { notifications, fetchNotificationList, deleteNotificationById, deleteAllNotifications } = useNotificationStore();
 const { uncheckdCnt, setUncheckedCnt } = useUserStore();
 //const {  deleteNotificationById, deleteAllNotifications } = useNotificationStore();
  useEffect(() => {
    setUncheckedCnt(0);
    checkNotificationAll();
    fetchNotificationList(); // 페이지가 처음 렌더링될 때 알림 데이터를 가져옴
  }, []); // 빈 배열을 전달하여 이펙트가 한 번만 실행되도록 함
  // const [notifications, setNotifications] = useState([
  //   {
  //     notificationId: 1,
  //     type: 'info',
  //     content: '새로운 알림이 도착했습니다.',
  //     createdAt: new Date().toISOString() // 현재 시간으로 설정
  //   },
  //   {
  //     notificationId: 2,
  //     type: 'warning',
  //     content: '중요한 공지사항입니다.',
  //     createdAt: new Date().toISOString() // 현재 시간으로 설정
  //   },
  //   {
  //     notificationId: 1,
  //     type: 'info',
  //     content: '새로운 알림이 도착했습니다. 새로운 알림이 도착했습니다.새로운 알림이 도착했습니다.새로운 알림이 도착했습니다.새로운 알림이 도착했습니다.',
  //     createdAt: new Date().toISOString() // 현재 시간으로 설정
  //   },
  //   {
  //     notificationId: 2,
  //     type: 'warning',
  //     content: '중요한 공지사항입니다.',
  //     createdAt: new Date().toISOString() // 현재 시간으로 설정
  //   },
  //   {
  //     notificationId: 1,
  //     type: 'info',
  //     content: '새로운 알림이 도착했습니다.',
  //     createdAt: new Date().toISOString() // 현재 시간으로 설정
  //   },
  //   {
  //     notificationId: 2,
  //     type: 'warning',
  //     content: '중요한 공지사항입니다.',
  //     createdAt: new Date().toISOString() // 현재 시간으로 설정
  //   }
  // ]);

  const handleDeleteNotification = async (notificationId) => {
    await deleteNotificationById(notificationId);
    // 삭제 후 상태 업데이트
    //setNotifications(notifications.filter(n => n.notificationId !== notificationId));
  };

  const handleDeleteAllNotifications = async () => {
    await deleteAllNotifications();
    //setNotifications([]); // 모든 알림을 삭제한 후 상태를 빈 배열로 설정
  };

  const timeSince = (date) => {
    const seconds = Math.floor((new Date() - new Date(date)) / 1000);
  
    let interval = seconds / 31536000;
  
    if (interval > 1) {
      return Math.floor(interval) + "년 전";
    }
    interval = seconds / 2592000;
    if (interval > 1) {
      return Math.floor(interval) + "달 전";
    }
    interval = seconds / 86400;
    if (interval > 1) {
      return Math.floor(interval) + "일 전";
    }
    interval = seconds / 3600;
    if (interval > 1) {
      return Math.floor(interval) + "시간 전";
    }
    interval = seconds / 60;
    if (interval > 1) {
      return Math.floor(interval) + "분 전";
    }
    return Math.floor(seconds) + "초 전";
  };
  

  return (
    <div className='notification-container'>
        <Link to="/main">
          <button className="settings-quit-button">
            <img src={arrowLeft} alt="Settings" />
          </button>
        </Link>
      <div className="notification-page">
        <div className="notification-header">
          <div className='notification-title'>알림 목록</div>
          <div className="notification-buttons">
            <button className="delete-all-button" onClick={handleDeleteAllNotifications}>전체 삭제</button>
            <Link to="/notification/setting">
              <button className="settings-button">설정</button>
            </Link>
          </div>
        </div>
        <div className="notification-list">
          {notifications.slice().reverse().map(notification => (
            <div key={notification.notificationId} className="notification-box-wrapper">
              <NotificationBox
                key={notification.notificationId}
                type={notification.type}
                content={notification.content}
                babyId={notification.babyId}
                time={timeSince(notification.createdAt)}
                onDelete={() => handleDeleteNotification(notification.notificationId)}
              />
            </div>
          ))}
        </div>
      </div>
    </div> 
  );
}

export default NotificationPage;
