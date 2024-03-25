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

export const submitFamilyCode = async (familyCode) => {
  console.log('familyCode: ', familyCode);
  try {
    const response = await axiosWrapper.post('/v1/users/family', { familyCode });
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
    console.error('가족코드 생성 오류!', error);
  }
}

export const addBabyInfo = async (formData) => {
  try {
      const response = await axiosWrapper.post('/v1/users/baby', formData);

      if (response.status === 200) {
          return response.data;
      } else {
          throw new Error('Failed to submit baby information');
      }
  } catch (error) {
      throw new Error('Error occurred while submitting baby information:', error);
  }
};

// userApi.js
export const updateBabyRole = async (data) => { // data를 직접 받도록 수정
  try {
      const response = await axiosWrapper.post('/v1/users/role', data); // 받은 data를 그대로 전송
      return response;
  } catch (error) {
      throw new Error('Failed to update baby roles:', error);
  }
};





