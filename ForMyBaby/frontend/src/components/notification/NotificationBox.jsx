import React, {useState, useEffect} from 'react';
import './notificationBox.css';
import { useUserStore } from '../../stores/UserStore';


const NotificationBox = ({ type, babyId, content, time, onDelete }) => {
  const {babyList} = useUserStore();
  const handleSwipeDelete = () => {
    onDelete(); // 부모 컴포넌트에서 전달받은 삭제 함수 실행
  }
  const [selectedBabyName, setSelectedBabyName] = useState("");
  const [expanded, setExpanded] = useState(false);
  const typeClass = type === 'info' ? 'info' : type === 'warning' ? 'warning' : ''
  const [notificationColor, setNotificationColor] = useState('');
  
  useEffect(() => {
    // 선택된 아이의 이름과 생일 업데이트
    const selectedBaby = babyList.find((baby) => baby.babyId === babyId);
    if (selectedBaby) {
      setSelectedBabyName(selectedBaby.babyName);
    }
  }, [babyList, babyId]);

  useEffect(() => {
    // 한 번만 색상을 무작위로 생성
    const randomColor = `rgb(${Math.floor(Math.random() * 256)}, ${Math.floor(Math.random() * 256)}, ${Math.floor(Math.random() * 256)})`;
    setNotificationColor(randomColor);
  }, []);


  const toggleExpanded = () => {
    setExpanded(!expanded);
  };


  return (
    <div className={`notification-box ${typeClass}`} onClick={handleSwipeDelete}> 
      <div className='noti-container'>
        <div className="notification-babyName">{selectedBabyName}</div>
         <div className={`notification-content ${expanded ? 'expanded' : ''}`} onClick={toggleExpanded}>{content}</div>
        <div className="notification-time">{time}</div>
        <button className="delete-notification-button" onClick={onDelete}>삭제</button>
      </div>
    </div>
  );
}

export default NotificationBox;
