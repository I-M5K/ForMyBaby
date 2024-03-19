import axiosWrapper from './axiosWrapper';

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