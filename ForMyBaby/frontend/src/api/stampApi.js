import axiosWrapper from './axiosWrapper';

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
      const response = await axiosWrapper.get(`/v1/stamp/${stampId}`);
      return response.data; // 스탬프 상세보기 데이터를 반환
    } catch (error) {
      console.error('스탬프 상세보기을=를 가져오는 중 에러 발생:', error);
      throw error; // 에러를 다시 던져서 상위 레벨에서 처리하도록 함
    }
};

export const modifyStamp = async (stampId, data) => {
    console.log('스탬프 수정하기!');
    try {
      const response = await axiosWrapper.patch(`/v1/stamp/${stampId}`, data);
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