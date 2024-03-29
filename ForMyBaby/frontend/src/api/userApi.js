import Role from './Role';
import axiosWrapper from './axiosWrapper';

// GPS 정보 보내기
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

// 가족 코드 인증하기
export const submitFamilyCode = async (familyCode) => {
  console.log('familyCode: ', familyCode);
  const role = 'none';
  try {
    const response = await axiosWrapper.get('/v1/users/family', { params: { familyCode } });
    console.log('Server Response:', response.data);
    return response.data; // 응답 데이터 반환
  } catch (error) {
    console.error('제출 오류!', error);
    throw error; // 에러를 상위로 전파
  }
}


// 첫 아이 정보 등록 + 신규 가족 코드 발급 받기
export const addFirstBabyInfo = async (formData) => {
  formData.append('role', Role.None);
  console.log('아이 신규 등록!');
  try {
    const response = await axiosWrapper.post('/v1/users/family', formData);
    console.log('가족코드', response.data);
    return response.data;
  } catch (error) {
    console.error('가족코드 생성 오류!', error);
  }
}

// 아이 정보 등록하기
export const addBabyInfo = async (formData) => {
  formData.append('role', Role.None);
  console.log('아이 추가 등록');
  try {
      const response = await axiosWrapper.post('/v1/users/family/add', formData);
      return response.data;
 
  } catch (error) {
      throw new Error('Error occurred while submitting baby information:', error);
  }
};

// 가족 관계 등록하기 (신규)
export const updateBabyRole = async (data) => { // data를 직접 받도록 수정
  try {
      const response = await axiosWrapper.post('/v1/users/role', data); // 받은 data를 그대로 전송
      return response;
  } catch (error) {
      throw new Error('Failed to update baby roles:', error);
  }
};


// 아이 정보 선택하기
export const selectBaby = async (babyId) => {
  try {
      const response = await axiosWrapper.get(`/v1/users/select?babyId=${babyId}`);
      
      return response;
  } catch (error) {
      throw new Error('Failed to select baby:', error);
  }
};

// // 아이 정보 목록 보기
// export const getBabyList = async (babyId) => {
//   try {
//       const response = await axiosWrapper.get(`/v1/users/select?babyId=${babyId}`);
//       return response;
//   } catch (error) {
//       throw new Error('Failed to select baby:', error);
//   }
// };








