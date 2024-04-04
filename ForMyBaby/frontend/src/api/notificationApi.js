import axiosWrapper from './axiosWrapper';

export const getNotificationList = async () => {
    console.log('알림 목록 가져오기!');
    try {
      const response = await axiosWrapper.get('/v1/notification/list');
      console.log(response.data);
      return response.data; // 알림 목록 데이터를 반환
    } catch (error) {
      console.error('알림 목록을 가져오는 중 에러 발생:', error);
      throw error; // 에러를 다시 던져서 상위 레벨에서 처리하도록 함
    }
};

export const deleteNotification = async (notificationId) => {
    console.log('해당 알림 삭제하기!');
    try {
        await axiosWrapper.delete(`/v1/notification/${notificationId}`);
        console.log('알림 삭제 완료!');
    } catch(error) {
        console.log('알림 삭제 에러 발생!');
    }
};

export const deleteNotificationAll = async () => {
    console.log('모든 알림 삭제하기!');
    try {
        await axiosWrapper.delete('/v1/notification/deleteall');
        console.log('모든 알림 삭제 완료!');
    } catch(error) {
        console.log('모든 알림 삭제 에러 발생!');
    }
};

export const sendNotificationSetting = async (list) => {
    try {
      // 서버로 변경된 상태 배열을 POST 요청으로 전송
      console.log('바뀐 설정 목록:', list);
      const response = await axiosWrapper.patch('/v1/notification/setting', list);
      console.log('결과', response.data);
    } catch (error) {
      console.error('알림 설정 에러', error);
    }
};

export const getNotificationSetting = async () => {
    try {
      // 서버로 변경된 상태 배열을 POST 요청으로 전송
      console.log("알림 설정 가져오기");
      const response = await axiosWrapper.get('/v1/notification/setting');
      console.log('가져온 설정 목록:', response.data);
      return response.data;
    } catch (error) {
      console.error('알림 설정 에러', error);
    }
};

export const checkNotification = async (notificationId) => {
  console.log('해당 알림 확인하기!');
  try {
      await axiosWrapper.patch(`/v1/notification/check/${notificationId}`);
      console.log('알림 확인 완료!');
  } catch(error) {
      console.log('알림 확인 에러 발생!');
  }
};

export const checkNotificationAll = async () => {
  console.log("모든 알림 확인하기!");
  try {
    await axiosWrapper.patch('/v1/notification/check/all');
    console.log('알림 확인 완료!');
} catch(error) {
    console.log('알림 확인 에러 발생!');
}
}