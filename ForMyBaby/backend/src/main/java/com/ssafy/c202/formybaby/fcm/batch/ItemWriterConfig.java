//package com.ssafy.c202.formybaby.fcm.batch;
//
//import com.ssafy.c202.formybaby.baby.entity.Baby;
//import com.ssafy.c202.formybaby.health.entity.Health;
//import com.ssafy.c202.formybaby.notification.entity.Notification;
//import com.ssafy.c202.formybaby.notification.service.NotificationService;
//import com.ssafy.c202.formybaby.vaccine.entity.Vaccine;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.batch.core.configuration.annotation.StepScope;
//import org.springframework.batch.item.ItemWriter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//@RequiredArgsConstructor
//@Slf4j
//@StepScope
//public class ItemWriterConfig {
//
////    private final NotificationService service;
//    @Autowired
//    private DataShareBean<Baby> babyShareBean;
//    @Autowired
//    private DataShareBean<Health> healthShareBean;
//    @Autowired
//    private DataShareBean<Vaccine> vaccineShareBean;
//
//    @Bean
//    @StepScope
//    public ItemWriter<Baby> babyWriter() {
//        return chunk -> {
//            for(Baby baby : chunk.getItems()) {
//                try{
//                    babyShareBean.putData((baby.getBabyId().toString()), baby);
//
//                } catch (Exception e) {
//                    log.info("Baby Share Bean error : {}", e.getMessage());
//                }
//            }
//        };
//    }
//    @Bean
//    @StepScope
//    public ItemWriter<Notification> notificationWriter() {
////        return chunk -> {
////            for(Health health : chunk.getItems()) {
////                try{
////                    healthShareBean.putData(Integer.toString(health.getHealthId()), health);
////
////                } catch (Exception e) {
////                    log.info("Health Share Bean error : {}", e.getMessage());
////                }
////            }
////        };
//        return null;
//    }
//}
