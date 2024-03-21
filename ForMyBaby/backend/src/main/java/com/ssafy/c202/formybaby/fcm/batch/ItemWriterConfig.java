package com.ssafy.c202.formybaby.fcm.batch;

import com.google.firebase.messaging.Notification;
import com.ssafy.c202.formybaby.baby.entity.Baby;
import com.ssafy.c202.formybaby.health.entity.Health;
import com.ssafy.c202.formybaby.notification.service.NotificationService;
import com.ssafy.c202.formybaby.vaccine.entity.Vaccine;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
@Slf4j
@StepScope
public class ItemWriterConfig {

//    private final NotificationService service;
    @Autowired
    private DataShareBean<Baby> babyShareBean;
    @Autowired
    private DataShareBean<Health> healthShareBean;
    @Autowired
    private DataShareBean<Vaccine> vaccineShareBean;

    public ItemWriter<Baby> babyWriter() {
        return chunk -> {
            try {
                babyShareBean.putData(chunk);
            } catch (Exception e) {
                log.info("babyAlarmWriter ERROR : {} ", e.getMessage());
            }

        };
    }
}
