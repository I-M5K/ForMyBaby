import axiosWrapper from './axiosWrapper';

export const getTodayData = async () => {
    console.log('오늘 데이터 가져오기!');
    try {
        const response = await axiosWrapper.get('/v1/sleep/today');
        console.log('오늘 데이터 가져오기 완료!', response.data);
        return response.data;
    } catch(error) {
        console.log('오늘 데이터 가져오기 에러 발생!');
    }
};

export const getWeekData = async (endAt) => {
    console.log('1주일 데이터 가져오기!');
    try {
        const response = await axiosWrapper.get('/v1/sleep/week', { params: { endAt: endAt } });
        console.log('1주일 데이터 가져오기 완료!', response.data);
        return response.data;
    } catch(error) {
        console.log('1주일 데이터 가져오기 에러 발생!');
    }
};


export const sendSleep = async (babyId) => {
    try {
        await axiosWrapper.get(`/v1/sleep/${babyId}`); // 받은 data를 그대로 전송
        console.log('잠듦 이벤트 저장 완료!');
    } catch (error) {
        throw new Error('잠듦 이벤트 저장 실패', error);
    }
};

export const sendAwake = async (babyId) => {
    try {
        await axiosWrapper.get(`/v1/sleep/awake/${babyId}`); // 받은 data를 그대로 전송
        console.log('깸 이벤트 저장 완료!');
    } catch (error) {
        throw new Error('깸 이벤트 저장 실패', error);
    }
};

export const sendDanger = async (dangerType) => {
    try {
        await axiosWrapper.get(`/v1/danger/${dangerType}`,); // 받은 data를 그대로 전송
        console.log('위험 이벤트 완료!');
    } catch (error) {
        throw new Error('위험 이벤트 저장 실패', error);
    }
};