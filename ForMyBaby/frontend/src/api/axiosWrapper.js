// AxiosWrapper.js

import axios from 'axios';

const AxiosWrapper = () => {
    const axiosInstance = axios.create({
        baseURL: 'http://j10c202.p.ssafy.io:8082', // 백엔드 API의 기본 URL
        //baseURL: 'http://localhost:8080', // 백엔드 API의 기본 URL
    });

    // 요청 전에 실행되는 인터셉터
    axiosInstance.interceptors.request.use(
        (config) => {
            // 로컬 스토리지에서 토큰을 가져와 헤더에 추가
            const token = localStorage.getItem('accessToken');
            if (token) {
                config.headers.Authorization = `${token}`;
            }
            return config;
        },
        (error) => {
            return Promise.reject(error);
        }
    );

    return axiosInstance;
};

export default AxiosWrapper();
