package com.ssafy.c202.formybaby.fcm.batch;

import com.ssafy.c202.formybaby.baby.entity.Baby;
import com.ssafy.c202.formybaby.fcm.entity.FCMMessage;
import com.ssafy.c202.formybaby.fcm.service.FCMService;
import com.ssafy.c202.formybaby.global.jpaEnum.NotificationType;
import com.ssafy.c202.formybaby.health.entity.Health;
import com.ssafy.c202.formybaby.notification.entity.Notification;
import com.ssafy.c202.formybaby.notification.mapper.NotificationMapper;
import com.ssafy.c202.formybaby.notification.service.NotificationService;
import com.ssafy.c202.formybaby.user.entity.Family;
import com.ssafy.c202.formybaby.vaccine.entity.Vaccine;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class ItemProcessorConfig {

    @Autowired
    private DataShareBean<Family> generalShareBean;
    @Autowired
    private DataShareBean<Family> dangerShareBean;

    private final NotificationMapper notificationMapper;

    private final NotificationService notificationService;

    private final FCMService fcmService;
    private static Logger logger = LoggerFactory.getLogger(DataShareBean.class);
    private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    @Bean
    @StepScope
    ItemProcessor<Health, List<Notification>> checkHealthProcessor() {
        LocalDate today = LocalDate.now();
        List<Notification> list = new ArrayList<>();
        return health -> {
            generalShareBean.map().forEach((s, family) -> {
                boolean isDay = false;
                boolean isWeek = false;
                LocalDate birthDate = family.getBaby().getBirthDate();
                int startAt = health.getStart_at();
                if(health.getHealthId() == 1) {
                    if(today.isEqual(birthDate.plusDays(7))){
                        isWeek = true;
                    } else if(today.isEqual(birthDate.plusDays(13))) {
                        isDay = true;
                    }
                } else {
                    LocalDate target = birthDate.plusMonths(startAt);
                    if(today.isEqual(target.minusWeeks(1))) {
                        isWeek = true;
                    } else if(today.isEqual(target.minusDays(1))) {
                        isDay = true;
                    }
                }
                if(isWeek) {
                    FCMMessage message = fcmService.toGeneralFcm(family.getFamilyCode(),
                            notificationService.createTitle(family, NotificationType.generalHealthWeek, health),
                            notificationService.createContent(family, NotificationType.generalHealthWeek, health),
                            "health");

                    log.info("HEALTH NOTIFICATION : {}", message);

                    fcmService.sendFCM(message);

                    list.add(notificationMapper.fromBatch(family, NotificationType.generalHealthWeek,
                            notificationService.createTitle(family, NotificationType.generalHealthWeek, health),
                            notificationService.createContent(family, NotificationType.generalHealthWeek, health)));
                }
                if(isDay) {
                    FCMMessage message = fcmService.toGeneralFcm(family.getFamilyCode(),
                            notificationService.createTitle(family, NotificationType.generalHealthWeek, health),
                            notificationService.createContent(family, NotificationType.generalHealthWeek, health),
                            "health");

                    log.info("HEALTH NOTIFICATION : {}", message);

                    fcmService.sendFCM(message);

                    list.add(notificationMapper.fromBatch(family, NotificationType.generalHealthDay,
                            notificationService.createTitle(family, NotificationType.generalHealthDay, health),
                            notificationService.createContent(family, NotificationType.generalHealthDay, health)));
                }
            });
            return list;
        };
    }

    @Bean
    @StepScope
    ItemProcessor<Vaccine, List<Notification>> checkVaccineProcessor() {
        LocalDate today = LocalDate.now();
        List<Notification> list = new ArrayList<>();
        return vaccine -> {
            generalShareBean.map().forEach((s, family) -> {
                boolean isDay = false;
                boolean isWeek = false;
                LocalDate birthDate = family.getBaby().getBirthDate();
                LocalDate target = birthDate.plusMonths(vaccine.getStart_at());
                if(today.isEqual(target.minusWeeks(1))) {
                    isWeek = true;
                } else if(today.isEqual(target.minusDays(1))) {
                    isDay = true;
                }

                if(isWeek) {
                    FCMMessage message = fcmService.toGeneralFcm(family.getFamilyCode(),
                            notificationService.createTitle(family, NotificationType.generalHealthWeek, vaccine),
                            notificationService.createContent(family, NotificationType.generalHealthWeek, vaccine),
                            "vaccine");

                    log.info("VACCINE NOTIFICATION : {}", message);

                    fcmService.sendFCM(message);

                    list.add(notificationMapper.fromBatch(family, NotificationType.generalVaccineWeek,
                            notificationService.createTitle(family, NotificationType.generalHealthWeek, vaccine),
                            notificationService.createContent(family, NotificationType.generalHealthWeek, vaccine)));
                }
                if(isDay) {
                    FCMMessage message = fcmService.toGeneralFcm(family.getFamilyCode(),
                            notificationService.createTitle(family, NotificationType.generalHealthWeek, vaccine),
                            notificationService.createContent(family, NotificationType.generalHealthWeek, vaccine),
                            "vaccine");

                    log.info("VACCINE NOTIFICATION : {}", message);

                    fcmService.sendFCM(message);

                    list.add(notificationMapper.fromBatch(family, NotificationType.generalVaccineDay,
                            notificationService.createTitle(family, NotificationType.generalVaccineDay, vaccine),
                            notificationService.createContent(family, NotificationType.generalVaccineDay, vaccine)));
                }
            });
            return list;
        };
    }
}
