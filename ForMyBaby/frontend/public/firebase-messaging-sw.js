self.addEventListener("install", function (e) {
    console.log("fcm sw install..");
    self.skipWaiting();
  });
  
  self.addEventListener("activate", function (e) {
    console.log("fcm sw activate..");
  });

  var type = ''
  
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
  
    type = data.type
    console.log(type);
    self.registration.showNotification(notificationTitle, notificationOptions);
  });
  
  self.addEventListener("notificationclick", function (event) {
    console.log("notification click");
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