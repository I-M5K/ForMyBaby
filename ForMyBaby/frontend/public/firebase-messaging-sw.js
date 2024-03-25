self.addEventListener("install", function (e) {
    console.log("fcm sw install..");
    self.skipWaiting();
  });
  
  self.addEventListener("activate", function (e) {
    console.log("fcm sw activate..");
  });

  var key = ''
  
  self.addEventListener("push", function (e) {
    console.log("push: ", e.data.json());
    if (!e.data.json()) return;
  
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
  
    key = data.key
    console.log(key);
    self.registration.showNotification(notificationTitle, notificationOptions);
  });
  
  self.addEventListener("notificationclick", function (event) {
    console.log("notification click");
    var url = "/main";
    console.log('key: ', key);
    if (key === 'danger'){ // 위험 감지
      url = '/baby-guard';
    } else if (key === 'health'){ // 건강검진
      url = '/timeline';
    } else if (key === 'vaccine'){ // 접종
      url = '/timeline';
    }
    event.notification.close();
    event.waitUntil(clients.openWindow(url));
  });