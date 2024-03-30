  // Import the functions you need from the SDKs you need
import { initializeApp } from "firebase/app";
import { getMessaging, getToken, onMessage } from "firebase/messaging";
import { sendDeviceToken } from "../api/fcmApi";
// TODO: Add SDKs for Firebase products that you want to use
// https://firebase.google.com/docs/web/setup#available-libraries

// Your web app's Firebase configuration
// For Firebase JS SDK v7.20.0 and later, measurementId is optional
const firebaseConfig = {
  apiKey: "AIzaSyDoj1PlYSKrUWQvHOKEfBo5r05CCHVV_zo",
  authDomain: "ssafy-ai-415702.firebaseapp.com",
  projectId: "ssafy-ai-415702",
  storageBucket: "ssafy-ai-415702.appspot.com",
  messagingSenderId: "153226356709",
  appId: "1:153226356709:web:07c5dffac2788e0b87710c",
  measurementId: "G-Y2C0CE431D"
};

// Initialize Firebase
const app = initializeApp(firebaseConfig);
const messaging = getMessaging(app);

export async function requestPermission() {
  console.log("권한 요청 중...");

  const permission = await Notification.requestPermission();
  if (permission === "denied") {
    console.log("알림 권한 허용 안됨");
    return;
  }

  console.log("알림 권한이 허용됨");

  const token = await getToken(messaging, {
    vapidKey: process.env.REACT_APP_VAPID_KEY,
  });

  if (token) {
    console.log('token: ', token);
    localStorage.setItem('fcmToken', token);
    sendDeviceToken(token); // 토큰 서버로 보내기
  } else console.log("Can not get Token");

  onMessage(messaging, (payload) => {
    console.log("메시지가 도착했습니다.", payload);
    // ...
  });
}

//requestPermission();