import useGeoLocation from "../../hooks/useGeolocation";
import { sendLocation } from '../../api/userApi'; 
import React, { useState, useEffect } from 'react';
import { requestPermission } from "../../FCM/firebase-messaging-sw";
import { useUserStore, useLocationStore } from '../../stores/UserStore'; // Zustand 스토어 import


const AgreePage = () => {
    //const location = useGeoLocation();
    const { id, name, email, fcm, setFcm } = useUserStore();
    //const { isExist, setIsExist } = useLocationStore();

    //requestPermission();
    console.log(id);
    console.log(name);
    console.log(email);

    useEffect(() => {
        // console.log('fetchData 직전');
        const fetchData = async () => {
            if (fcm == null){
                requestPermission();
                setFcm(localStorage.getItem('fcmToken'));
                localStorage.removeItem('fcmToken');
            }
            // console.log('fetchData 직후');
        };
        fetchData();
    }, []); // location과 jwt를 의존성 배열에 추가하여 useEffect가 적절하게 실행되도록 함
    return (
        <div>
            <h1>동의 페이지</h1>
        </div>
    );
}

export default AgreePage;