// import { useUserStore } from '../src/stores/UserStore';
// import { selectBaby } from '../src/api/userApi';
// window.onload = function(){
  self.addEventListener("install", function (e) {
    console.log("fcm sw install..");
    self.skipWaiting();
  });
  
  self.addEventListener("activate", function (e) {
    console.log("fcm sw activate..");
  });

//  const { babySelected, setBabySelected, uncheckedCnt, setUncheckedCnt } = useUserStore();

  var type = ''
  var babyId = 0

  self.addEventListener("push", function (e) {
    console.log("push: ", e.data.json());
    if (!e.data.json()) return;
    
//    setUncheckedCnt(uncheckedCnt+1);

    const data = e.data.json().data;
    const resultData = e.data.json().notification;
    const notificationTitle = resultData.title;
    const notificationOptions = {
      body: resultData.body,
      icon: resultData.image,
      tag: resultData.tag,
      ...resultData,
    };
    console.log("push: ", { resultData, notificationTitle, notificationOptions });
  
    babyId = data.babyId;
    type = data.type
    console.log("type : ", type);
    self.registration.showNotification(notificationTitle, notificationOptions);
  });
  
  self.addEventListener("notificationclick", function (event) {
    console.log("notification click");
    // if (babySelected != babyId){
    //   setBabySelected(babyId);
    //   console.log("선택 아이 정보 바꾸기!");
    //   selectBaby(babyId);
    // }
    var url = "/main?babyId=${babyId}";
    console.log('type: ', type);
    if (type === 'danger'){ // 위험 감지
      url = '/baby-guard?babyId=${babyId}';
    } else if (type === 'health'){ // 건강검진
      url = '/timeline?babyId=${babyId}%?type=${type}';
    } else if (type === 'vaccine'){ // 접종
      url = '/timeline?babyId=${babyId}%?type=${type}';
    }
    event.notification.close();
    event.waitUntil(clients.openWindow(url));
  });

// importScripts(
// 	"https://www.gstatic.com/firebasejs/10.9.0/firebase-app-compat.js"
// );
// importScripts(
// 	"https://www.gstatic.com/firebasejs/10.9.0/firebase-messaging-compat.js"
// );

// Initialize the Firebase app in the service worker by passing in
// your app's Firebase config object.
// https://firebase.google.com/docs/web/setup#config-object

// const firebaseConfig = {
//   apiKey: "AIzaSyDoj1PlYSKrUWQvHOKEfBo5r05CCHVV_zo",
//   authDomain: "ssafy-ai-415702.firebaseapp.com",
//   projectId: "ssafy-ai-415702",
//   storageBucket: "ssafy-ai-415702.appspot.com",
//   messagingSenderId: "153226356709",
//   appId: "1:153226356709:web:07c5dffac2788e0b87710c",
//   measurementId: "G-Y2C0CE431D"
// };

// firebase.initializeApp(firebaseConfig);

// // Retrieve an instance of Firebase Messaging so that it can handle background
// // messages.
// const messaging = firebase.messaging();

// messaging.onBackgroundMessage((payload) => {
// 	console.log(
// 		"[firebase-messaging-sw.js] Received background message ",
// 		payload
// 	);
// 	// const data = payload?.notification ?? {};
// 	// // Customize notification here
// 	// const notificationTitle = data.title ?? "";
// 	// const notificationOptions = {
// 	// 	body: data.body,
// 	// 	icon: data.icon,
// 	// };

// 	// self.registration.showNotification(notificationTitle, notificationOptions);
// });
