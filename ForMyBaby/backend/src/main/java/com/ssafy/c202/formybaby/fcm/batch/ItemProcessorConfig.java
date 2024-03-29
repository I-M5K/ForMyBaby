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
import java.util.concurrent.atomic.AtomicInteger;

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
        return health -> {
            LocalDate today = LocalDate.now();
            List<Notification> list = new ArrayList<>();
            generalShareBean.map().forEach((s, family) -> {
                boolean isDay = false;
                boolean isWeek = false;
                LocalDate birthDate = family.getBaby().getBirthDate();
                String babyId = String.valueOf(family.getBaby().getBabyId());
                String fcmToken = family.getUser().getFcmToken();
                int startAt = health.getStartAt();
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
                    FCMMessage message = fcmService.toGeneralFcm(
                            notificationService.createTitle(family, NotificationType.generalHealthWeek, health),
                            notificationService.createContent(family, NotificationType.generalHealthWeek, health),
                            fcmToken, "health", babyId);

                    log.info("HEALTH NOTIFICATION : {}", message);

                    fcmService.sendFCM(message);

                    list.add(notificationMapper.fromBatch(family, NotificationType.generalHealthWeek,
                            notificationService.createTitle(family, NotificationType.generalHealthWeek, health),
                            notificationService.createContent(family, NotificationType.generalHealthWeek, health), false));
                }
                if(isDay) {
                    FCMMessage message = fcmService.toGeneralFcm(
                            notificationService.createTitle(family, NotificationType.generalHealthDay, health),
                            notificationService.createContent(family, NotificationType.generalHealthDay, health),
                            fcmToken, "health", babyId);

                    log.info("HEALTH NOTIFICATION : {}", message);

                    fcmService.sendFCM(message);

                    list.add(notificationMapper.fromBatch(family, NotificationType.generalHealthDay,
                            notificationService.createTitle(family, NotificationType.generalHealthDay, health),
                            notificationService.createContent(family, NotificationType.generalHealthDay, health), false));
                }
            });
            return list;
        };
    }

    @Bean
    @StepScope
    ItemProcessor<Vaccine, List<Notification>> checkVaccineProcessor() {
        AtomicInteger a = new AtomicInteger(1);
        return vaccine -> {
            LocalDate today = LocalDate.now();
            List<Notification> list = new ArrayList<>();
            generalShareBean.map().forEach((s, family) -> {
                System.out.println("AAAAAAA " + a.getAndIncrement());
                boolean isDay = false;
                boolean isWeek = false;
                String fcmToken = family.getUser().getFcmToken();
                String babyId = String.valueOf(family.getBaby().getBabyId());
                LocalDate birthDate = family.getBaby().getBirthDate();
                LocalDate target = birthDate.plusMonths(vaccine.getStartAt());
                System.out.println(target.minusDays(1));
                if(today.isEqual(target.minusWeeks(1))) {
                    isWeek = true;
                } else if(today.isEqual(target.minusDays(1))) {
                    isDay = true;
                }

                if(isWeek) {
                    FCMMessage message = fcmService.toGeneralFcm(
                            notificationService.createTitle(family, NotificationType.generalVaccineWeek, vaccine),
                            notificationService.createContent(family, NotificationType.generalVaccineWeek, vaccine),
                            fcmToken, "vaccine", babyId);

                    log.info("VACCINE WEEK NOTIFICATION : {}", message);

                    fcmService.sendFCM(message);

                    list.add(notificationMapper.fromBatch(family, NotificationType.generalVaccineWeek,
                            notificationService.createTitle(family, NotificationType.generalVaccineWeek, vaccine),
                            notificationService.createContent(family, NotificationType.generalVaccineWeek, vaccine), false));
                }
                if(isDay) {
                    FCMMessage message = fcmService.toGeneralFcm(
                            notificationService.createTitle(family, NotificationType.generalVaccineWeek, vaccine),
                            notificationService.createContent(family, NotificationType.generalVaccineWeek, vaccine),
                            fcmToken, "vaccine", babyId);

                    log.info("VACCINE DAY NOTIFICATION : {}", message);

                    fcmService.sendFCM(message);

                    list.add(notificationMapper.fromBatch(family, NotificationType.generalVaccineDay,
                            notificationService.createTitle(family, NotificationType.generalVaccineDay, vaccine),
                            notificationService.createContent(family, NotificationType.generalVaccineDay, vaccine), false));
                }
            });
            return list;
        };
    }

    @Bean
    @StepScope
    ItemProcessor<Family, Family> checkBeanProcessor() {
        LocalDate today = LocalDate.now();
        generalShareBean.map().forEach((s, family) -> {
            if(today.minusYears(13).isAfter(family.getBaby().getBirthDate())) {
                Family out = generalShareBean.map().remove(s);
                log.info("Birth Exceeded : {} ", out);
            }
        });
        log.info("Stored Families : {} ", generalShareBean.map());
        return f -> f;
    }
}
