package com.ssafy.c202.formybaby.notification.service;


import com.ssafy.c202.formybaby.baby.entity.Baby;
import com.ssafy.c202.formybaby.global.jpaEnum.NotificationType;
import com.ssafy.c202.formybaby.health.entity.Health;
import com.ssafy.c202.formybaby.notification.dto.request.SettingUpdateRequest;
import com.ssafy.c202.formybaby.notification.dto.response.NotificationReadResponse;
import com.ssafy.c202.formybaby.notification.dto.response.SettingReadResponse;
import com.ssafy.c202.formybaby.notification.entity.Notification;
import com.ssafy.c202.formybaby.sleep.entity.Danger;
import com.ssafy.c202.formybaby.user.entity.Family;
import com.ssafy.c202.formybaby.user.entity.User;
import com.ssafy.c202.formybaby.vaccine.entity.Vaccine;

import java.util.List;

public interface NotificationService {

    String createTitle(Family family, NotificationType type, Vaccine vaccine);
    String createTitle(Family family, NotificationType type, Health health);
    String createContent(Family family, NotificationType type, Vaccine vaccine);
    String createContent(Family family, NotificationType type, Health health);
    Notification createDangerNotification(List<User> users, List<Baby> babies, Danger danger);
    List<NotificationReadResponse> getList(Long userId, Long babyId);
    SettingReadResponse getSetting(Long userId);
    void checkNotification(Long notificationId);
    SettingReadResponse updateSetting(SettingUpdateRequest settingUpdateRequest, Long userId);
    void delete(Long notificationId);
    void deleteAll(Long userId, Long babyId);
}
