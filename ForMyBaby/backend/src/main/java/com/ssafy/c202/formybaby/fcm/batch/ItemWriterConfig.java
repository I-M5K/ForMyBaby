package com.ssafy.c202.formybaby.fcm.batch;

import com.ssafy.c202.formybaby.baby.entity.Baby;
import com.ssafy.c202.formybaby.health.entity.Health;
import com.ssafy.c202.formybaby.notification.entity.Notification;
import com.ssafy.c202.formybaby.notification.repository.NotificationRepository;
import com.ssafy.c202.formybaby.notification.service.NotificationService;
import com.ssafy.c202.formybaby.user.entity.Family;
import com.ssafy.c202.formybaby.vaccine.entity.Vaccine;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@RequiredArgsConstructor
@Slf4j
@StepScope
public class ItemWriterConfig {

    private final NotificationService notificationService;
    private final NotificationRepository notificationRepository;
    @Autowired
    private DataShareBean<Family> generalShareBean;

    @Bean
    @StepScope
    public ItemWriter<Family> generalFamilyWriter() {
        return chunk -> {
            for(Family family : chunk.getItems()) {
                try{
                    generalShareBean.putData((family.getFamilyId().toString()), family);

                } catch (Exception e) {
                    log.info("Baby Share Bean error : {}", e.getMessage());
                }
            }
        };
    }
    @Bean
    @StepScope
    public ItemWriter<List<Notification>> notificationWriter() {
        return lists -> {
            for(List<Notification> list : lists) {
                notificationRepository.saveAll(list);
            }
        };
    }
}
