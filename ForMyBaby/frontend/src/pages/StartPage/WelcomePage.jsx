import useGeoLocation from "../../hooks/useGeolocation";
import { sendLocation } from '../../api/userApi'; 
import React, { useState, useEffect } from 'react';
import { useRecoilValue } from 'recoil';
import { useRecoilState } from 'recoil';

// import "../../FCM/firebase-messaging-sw.js";
import { requestPermission } from "../../FCM/firebase-messaging-sw";
import { userDataState } from "../../atoms/userDataState";

const WelcomePage = () => {
    const location = useGeoLocation();
    const [userData, setUserData] = useRecoilState(userDataState);
    console.log(userData);
    //requestPermission();

    useEffect(() => {
        // console.log('fetchData 직전');
        const fetchData = async () => {
            if (userData.fcm == null){
                requestPermission();
                setUserData(userData => ({
                    ...userData,
                    fcm: localStorage.getItem('fcmToken')
                }));
            }
            // console.log('fetchData 직후');
            if (location && location.loaded && location.coordinates) {
                //console.log('useEffect 안 if 문');
                await sendLocation(location.coordinates.lat, location.coordinates.lng);
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