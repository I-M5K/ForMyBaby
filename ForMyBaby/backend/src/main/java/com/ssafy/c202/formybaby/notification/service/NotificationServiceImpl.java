package com.ssafy.c202.formybaby.notification.service;

import com.ssafy.c202.formybaby.baby.entity.Baby;
import com.ssafy.c202.formybaby.global.jpaEnum.NotificationType;
import com.ssafy.c202.formybaby.global.util.StringCheck;
import com.ssafy.c202.formybaby.health.entity.Health;
import com.ssafy.c202.formybaby.notification.dto.request.SettingUpdateRequest;
import com.ssafy.c202.formybaby.notification.dto.response.SettingReadResponse;
import com.ssafy.c202.formybaby.notification.entity.Notification;
import com.ssafy.c202.formybaby.notification.entity.NotificationCheck;
import com.ssafy.c202.formybaby.notification.mapper.NotificationMapper;
import com.ssafy.c202.formybaby.notification.repository.NotificationRepository;
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
    private final NotificationRepository notificationRepository;

    @Override
    public String createTitle(Family family, NotificationType type, Vaccine vaccine) {
        if(type.equals(NotificationType.generalVaccineDay)) {
            return StringCheck.getPostWord(family.getBaby().getBabyName().substring(1), "이", "") + "의 "
                    + vaccine.getTarget() + " 예방접종(" + vaccine.getType() + ")이 1일 전입니다.";
        } else {
            return StringCheck.getPostWord(family.getBaby().getBabyName().substring(1), "이", "") + "의 "
                    + vaccine.getTarget() + " 예방접종(" + vaccine.getType() + ")이 7일 전입니다.";
        }
    }

    @Override
    public String createTitle(Family family, NotificationType type, Health health) {
        if(type.equals(NotificationType.generalHealthDay)) {
            return StringCheck.getPostWord(family.getBaby().getBabyName().substring(1), "이", "") + "의 "
                    + StringCheck.getPostWord(health.getHealthTitle(), "이", "가") + "1일 전입니다.";
        } else {
            return StringCheck.getPostWord(family.getBaby().getBabyName().substring(1), "이", "") + "의 "
                    + StringCheck.getPostWord(health.getHealthTitle(), "이", "가") + "7일 전입니다.";
        }
    }

    @Override
    public String createContent(Family family, NotificationType type, Vaccine vaccine) {
        return vaccine.getTarget() + " 예방접종(" + vaccine.getType() + ")은 생후 " + vaccine.getStartAt()
                + "개월에서 " + vaccine.getEndAt() + "개월 사이에 접종 받아야 합니다.";
    }

    @Override
    public String createContent(Family family, NotificationType type, Health health) {
        if(health.getHealthId() == 1) {
            return StringCheck.getPostWord(health.getHealthTitle(), "은", "는") + health.getStartAt()
                    + "일에서 " + health.getEndAt() + "일 사이에 검진 받아야 합니다.";
        }
        return StringCheck.getPostWord(health.getHealthTitle(), "은", "는") + health.getStartAt()
                + "개월에서 " + health.getEndAt() + "개월 사이에 검진 받아야 합니다.";
    }

    @Override
    public Notification createDangerNotification(List<User> users, List<Baby> babies, Danger danger) {
        return null;
    }

    @Override
    public NotificationCheck createIsChecked(Long notificationId) {
        return null;
    }

    @Override
    public List<Notification> getList(Long userId) {
        return notificationRepository.findAllByUserUserId(userId);
    }

    @Override
    public SettingReadResponse getSetting(Long userId) {
        return notificationRepository.findSettingByUserId(userId);
    }

    @Override
    public void checkNotification(Long notificationId) {
        notificationRepository.checkNotification(notificationId);
    }

    @Override
    public SettingReadResponse updateSetting(SettingUpdateRequest settingUpdateRequest, Long userId) {
        return notificationRepository.updateSettingByUserId(settingUpdateRequest.isGeneral(),
                settingUpdateRequest.isDanger(), settingUpdateRequest.isSound(), userId);
    }

    @Override
    public void delete(Long notificationId) {
        notificationRepository.deleteByNotificationId(notificationId);
    }

    @Override
    public void deleteAll(Long userId) {
        notificationRepository.deleteAllByUserId(userId);
    }
}
