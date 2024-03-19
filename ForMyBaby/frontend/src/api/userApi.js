import axios from 'axios';
import axiosWrapper from './axiosWrapper';

export const sendLocation = async (latitude, longitude) => {
    console.log('userApi 안!');
    if (latitude !== null && longitude !== null) {
      try {
        console.log('latitude, longitude' + latitude, longitude)
        // axios를 사용하여 백엔드로 POST 요청 보내기
        await axiosWrapper.post('/oauth/location', { latitude, longitude });
        console.log('위도와 경도 정보를 백엔드로 전송했습니다.');
      } catch (error) {
        console.error('백엔드로 위도와 경도 정보를 보내는 중 오류가 발생했습니다.', error);
      }
    }
};

export const getNotificationList = async () => {
  console.log('알림 목록 가져오기!');
  try {
    const response = await axiosWrapper.get('/notification/list');
    return response.data; // 알림 목록 데이터를 반환
  } catch (error) {
    console.error('알림 목록을 가져오는 중 에러 발생:', error);
    throw error; // 에러를 다시 던져서 상위 레벨에서 처리하도록 함
  }
};
