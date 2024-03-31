import axiosWrapper from './axiosWrapper';

export const createStampByUser = async () => {
  console.log('유저 스탬프 등록!');
  try {
    const response = await axiosWrapper.post('/v1/stamp');
    console.log(response);
  } catch (error) {
    console.error('스탬프 목록을 가져오는 중 에러 발생:', error);
    throw error; // 에러를 다시 던져서 상위 레벨에서 처리하도록 함
  }
};

export const createStampByAI = async (data) => {
  console.log('AI 스탬프 등록!');
  try {
    const response = await axiosWrapper.post('/v1/stamp/ai', data);
    console.log(response);
  } catch (error) {
    console.error('스탬프 목록을 가져오는 중 에러 발생:', error);
    throw error; // 에러를 다시 던져서 상위 레벨에서 처리하도록 함
  }
};

export const getStampList = async () => {
    console.log('스탬프 목록 가져오기!');
    try {
      const response = await axiosWrapper.get('/v1/stamp');
      return response.data; // 스탬프 목록 데이터를 반환
    } catch (error) {
      console.error('스탬프 목록을 가져오는 중 에러 발생:', error);
      throw error; // 에러를 다시 던져서 상위 레벨에서 처리하도록 함
    }
};

export const getStampDetail = async (stampId) => {
    console.log('스탬프 상세보기 가져오기!');
    try {
      const response = await axiosWrapper.get(`/v1/stamp/detail/${stampId}`);
      return response.data; // 스탬프 상세보기 데이터를 반환
    } catch (error) {
      console.error('스탬프 상세보기를 가져오는 중 에러 발생:', error);
      throw error; // 에러를 다시 던져서 상위 레벨에서 처리하도록 함
    }
};

export const modifyUserStamp = async (stampId, data) => {
    console.log('유저 스탬프 수정하기!');
    try {
      const response = await axiosWrapper.put(`/v1/stamp/${stampId}`, data);
      console.log('스탬프 수정 성공!');
    } catch (error) {
      console.error('스탬프 수정 중 에러 발생:', error);
      throw error; // 에러를 다시 던져서 상위 레벨에서 처리하도록 함
    }
};

export const modifyAIStamp = async (stampId, data) => {
  console.log('AI 스탬프 수정하기!');
  try {
    const response = await axiosWrapper.patch(`/v1/stamp/ai/${stampId}`, data);
    console.log('스탬프 수정 성공!');
  } catch (error) {
    console.error('스탬프 수정 중 에러 발생:', error);
    throw error; // 에러를 다시 던져서 상위 레벨에서 처리하도록 함
  }
};

export const deleteStamp = async (stampId) => {
    console.log('스탬프 삭제하기!');
    try {
      await axiosWrapper.delete(`/v1/stamp/${stampId}`);
      console.log('스탬프 삭제하기');
    } catch (error) {
      console.error('스탬프 삭제하는 중 에러 발생:', error);
      throw error; // 에러를 다시 던져서 상위 레벨에서 처리하도록 함
    }
};