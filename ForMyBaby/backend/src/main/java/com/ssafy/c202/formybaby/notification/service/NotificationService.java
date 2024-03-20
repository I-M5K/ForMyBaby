package com.ssafy.c202.formybaby.notification.service;


import com.ssafy.c202.formybaby.baby.entity.Baby;
import com.ssafy.c202.formybaby.notification.entity.Notification;
import com.ssafy.c202.formybaby.notification.entity.NotificationCheck;
import com.ssafy.c202.formybaby.sleep.entity.Danger;
import com.ssafy.c202.formybaby.user.entity.User;

import java.util.List;

public interface NotificationService {

//    건강검진 / 예방접종 데이터 넣기
//    Notification createDayNotification(List<User> users, List<Baby> babies, );
//    Notification createWeekNotification(List<User> users, List<Baby> babies, );
    Notification createDangerNotification(List<User> users, List<Baby> babies, Danger danger);
    NotificationCheck isChecked(Long notificationId);
}
