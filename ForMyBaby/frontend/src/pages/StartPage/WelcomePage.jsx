import useGeoLocation from "../../hooks/useGeolocation";
import { sendLocation } from '../../api/userApi'; 
import React, { useState, useEffect } from 'react';
import { requestPermission } from "../../FCM/firebase-messaging-sw";
import { useUserStore } from '../../stores/UserStore'; // Zustand 스토어 import


const WelcomePage = () => {
    const location = useGeoLocation();
    const { id, name, email, fcm, setFcm } = useUserStore();
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
            if (location && location.loaded && location.coordinates) {
                //console.log('useEffect 안 if 문');
                if(location.coordinates.lat != null && location.coordinates.lng != null){
                    await sendLocation(location.coordinates.lat, location.coordinates.lng);
                }
            }
        };
        fetchData();
    }, [location]); // location과 jwt를 의존성 배열에 추가하여 useEffect가 적절하게 실행되도록 함
    return (
        <div>
            <h1>환영 페이지</h1>
        </div>
    );
}

export default WelcomePage;