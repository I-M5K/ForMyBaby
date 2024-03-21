import axios from 'axios';
import axiosWrapper from './axiosWrapper';

export const sendLocation = async (latitude, longitude) => {
    console.log('userApi 안!');
    if (latitude !== null && longitude !== null) {
      try {
        console.log('latitude, longitude' + latitude, longitude)
        // axios를 사용하여 백엔드로 POST 요청 보내기
        await axiosWrapper.post('/v1/users/location', { latitude, longitude });
        console.log('위도와 경도 정보를 백엔드로 전송했습니다.');
      } catch (error) {
        console.error('백엔드로 위도와 경도 정보를 보내는 중 오류가 발생했습니다.', error);
      }
    }
};

export const submitFamilyCode = async (code) => {
  console.log('familyCode: ', code);
  try {
    const response = await axiosWrapper.post('/v1/users/family', { code });
    console.log('Server Response:', response.data);
    if (response == 1){ // 옳은 가족 코드
      return 1;
    } else { // 없는 가족 토드
      return 0;
    } 
  } catch (error) {
    console.error('제출 오류!', error);
  }
}

export const getFamilyCode = async () => {
  console.log('가족코드받기!');
  try {
    const response = await axiosWrapper.get('/v1/users/family');
    console.log('가족코드', response.data);
    return response.data;
  } catch (error) {
    console.error('제출 오류!', error);
  }
}


