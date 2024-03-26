package com.ssafy.c202.formybaby.notification.service;

import com.ssafy.c202.formybaby.baby.entity.Baby;
import com.ssafy.c202.formybaby.global.jpaEnum.NotificationType;
import com.ssafy.c202.formybaby.health.entity.Health;
import com.ssafy.c202.formybaby.notification.entity.Notification;
import com.ssafy.c202.formybaby.notification.entity.NotificationCheck;
import com.ssafy.c202.formybaby.notification.mapper.NotificationMapper;
import com.ssafy.c202.formybaby.sleep.entity.Danger;
import com.ssafy.c202.formybaby.user.entity.Family;
import com.ssafy.c202.formybaby.user.entity.User;
import com.ssafy.c202.formybaby.vaccine.entity.Vaccine;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
@Transactional
public class NotificationServiceImpl implements NotificationService{

    private final NotificationMapper notificationMapper;
    @Override
    public String createTitle(Family family, NotificationType type, Vaccine vaccine) {
        if(type.equals(NotificationType.generalVaccineDay)) {
            return "Vaccine D-1 Title";
        } else {
            return "Vaccine D-7 Title";
        }
    }

    @Override
    public String createTitle(Family family, NotificationType type, Health health) {
        if(type.equals(NotificationType.generalHealthDay)) {
            return "Health D-1 Title";
        } else {
            return "Health D-7 Title";
        }
    }

    @Override
    public String createContent(Family family, NotificationType type, Vaccine vaccine) {
        if(type.equals(NotificationType.generalVaccineDay)) {
            return "Vaccine D-1 Content";
        } else {
            return "Vaccine D-7 Content";
        }
    }

    @Override
    public String createContent(Family family, NotificationType type, Health health) {
        if(type.equals(NotificationType.generalHealthDay)) {
            return "Health D-1 Content";
        } else {
            return "Health D-7 Content";
        }
    }

    @Override
    public Notification createDangerNotification(List<User> users, List<Baby> babies, Danger danger) {
        return null;
    }

    @Override
    public NotificationCheck isChecked(Long notificationId) {
        return null;
    }
}
