import useGeoLocation from "../../hooks/useGeolocation";
import { sendLocation } from '../../api/userApi'; 
import React, { useState, useEffect } from 'react';
import { requestPermission } from "../../FCM/firebase-messaging-sw";
import { useUserStore, useLocationStore } from '../../stores/UserStore'; // Zustand 스토어 import
import { Link } from 'react-router-dom';
import './AgreePage.css'; // AgreePage의 CSS 파일 import

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
                //localStorage.removeItem('fcmToken');
            }
            // console.log('fetchData 직후');
        };
        fetchData();
    }, []); // location과 jwt를 의존성 배열에 추가하여 useEffect가 적절하게 실행되도록 함
    return (
        <div className="agree-page-container"> {/* 클래스 이름을 CSS 파일에서 정의한 것으로 변경 */}
            <h1 className="agree-page-title">동의 페이지</h1> {/* 클래스 이름을 CSS 파일에서 정의한 것으로 변경 */}
            <p className="agree-page-description">위치 및 푸시 알림 사용에 동의하시겠습니까?</p> {/* 클래스 이름을 CSS 파일에서 정의한 것으로 변경 */}
            <Link to='/family'>
                <button className="agree-button">동의</button> {/* 클래스 이름을 CSS 파일에서 정의한 것으로 변경 */}
            </Link>
        </div>
    );
}

export default AgreePage;
