// Import the functions you need from the SDKs you need
import { initializeApp } from "firebase/app";
import { getMessaging, getToken, onMessage } from "firebase/messaging";
import { sendDeviceToken } from "../api/fcmApi";
// TODO: Add SDKs for Firebase products that you want to use
// https://firebase.google.com/docs/web/setup#available-libraries

// Your web app's Firebase configuration
// For Firebase JS SDK v7.20.0 and later, measurementId is optional
const firebaseConfig = {
  apiKey: "AIzaSyCpQRys0g1wtVFi7QFDfJykINwMmmBxDLo",
  authDomain: "formybaby-3f058.firebaseapp.com",
  projectId: "formybaby-3f058",
  storageBucket: "formybaby-3f058.appspot.com",
  messagingSenderId: "1037876412711",
  appId: "1:1037876412711:web:04a4fd6a91519a8809a902",
  measurementId: "G-D9110MK113"
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
    sendDeviceToken(token);
  } else console.log("Can not get Token");

  onMessage(messaging, (payload) => {
    console.log("메시지가 도착했습니다.", payload);
    // ...
  });
}

//requestPermission();