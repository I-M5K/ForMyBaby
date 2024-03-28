import { useUserStore } from '../src/stores/UserStore';
import { selectBaby } from '../src/api/userApi';

  self.addEventListener("install", function (e) {
    console.log("fcm sw install..");
    self.skipWaiting();
  });
  
  self.addEventListener("activate", function (e) {
    console.log("fcm sw activate..");
  });

  const { babySelected, setBabySelected, uncheckedCnt, setUncheckedCnt } = useUserStore();

  var type = ''
  var babyId = 0

  self.addEventListener("push", function (e) {
    console.log("push: ", e.data.json());
    if (!e.data.json()) return;
    
    setUncheckedCnt(uncheckedCnt+1);

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
    console.log(type);
    self.registration.showNotification(notificationTitle, notificationOptions);
  });
  
  self.addEventListener("notificationclick", function (event) {
    console.log("notification click");
    if (babySelected != babyId){
      setBabySelected(babyId);
      console.log("선택 아이 정보 바꾸기!");
      selectBaby(babyId);
    }
    var url = "/main";
    console.log('type: ', type);
    if (type === 'danger'){ // 위험 감지
      url = '/baby-guard';
    } else if (type === 'health'){ // 건강검진
      url = '/timeline';
    } else if (type === 'vaccine'){ // 접종
      url = '/timeline';
    }
    event.notification.close();
    event.waitUntil(clients.openWindow(url));
  });