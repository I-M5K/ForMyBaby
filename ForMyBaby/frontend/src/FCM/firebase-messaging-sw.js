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

  // 서비스 워커가 활성화된 후에 푸시 알림 구독을 시도
  navigator.serviceWorker.ready.then(async (registration) => {
    const token = await getToken(messaging, {
      vapidKey: process.env.REACT_APP_VAPID_KEY,
    });

    if (token) {
      console.log('푸시 알림을 위한 토큰: ', token);
      localStorage.setItem('fcmToken', token);
      sendDeviceToken(token); // 토큰 서버로 보내기
      window.location.reload();
    } else {
      console.log("푸시 알림 토큰을 가져올 수 없습니다.");
    }

    onMessage(messaging, (payload) => {
      console.log("메시지가 도착했습니다.", payload);
      // 푸시 알림을 수신한 후의 로직을 추가할 수 있습니다.
    });
  }).catch((error) => {
    console.error('서비스 워커가 아직 활성화되지 않았습니다.', error);
    // 에러가 발생했을 때 페이지를 새로고침
    window.location.reload();
  });
}

//requestPermission();