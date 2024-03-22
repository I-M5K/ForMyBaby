//package com.ssafy.c202.formybaby.fcm.batch;
//
//import com.ssafy.c202.formybaby.baby.entity.Baby;
//import com.ssafy.c202.formybaby.health.entity.Health;
//import com.ssafy.c202.formybaby.notification.entity.Notification;
//import com.ssafy.c202.formybaby.vaccine.entity.Vaccine;
//import lombok.AllArgsConstructor;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.repository.JobRepository;
//import org.springframework.batch.core.step.builder.StepBuilder;
//import org.springframework.batch.item.ItemProcessor;
//import org.springframework.batch.item.ItemReader;
//import org.springframework.batch.item.ItemWriter;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.transaction.PlatformTransactionManager;
//
//@Configuration
//@AllArgsConstructor
//public class StepConfig {
//    public static final Integer CHUNK_SIZE = 100;
//
//    @Bean
//    public Step getBabies(JobRepository jobRepository, PlatformTransactionManager transactionManager,
//                          ItemReader<Baby> babyReader, ItemWriter<Baby> babyWriter) {
//        return new StepBuilder("getBabies", jobRepository)
//                .<Baby, Baby>chunk(CHUNK_SIZE, transactionManager)
//                .reader(babyReader)
//                .writer(babyWriter)
//                .build();
//    }
//    @Bean
//    public Step getHealth(JobRepository jobRepository, PlatformTransactionManager transactionManager,
//                          ItemReader<Health> healthReader, ItemProcessor<Health, Notification> checkHealthProcessor,
//                          ItemWriter<Notification> notificationWriter) {
//        return new StepBuilder("getHealth", jobRepository)
//                .<Health, Notification>chunk(CHUNK_SIZE, transactionManager)
//                .reader(healthReader)
//                .processor(checkHealthProcessor)
//                .writer(notificationWriter)
//                .build();
//
//    }
//    @Bean
//    public Step getVaccine(JobRepository jobRepository, PlatformTransactionManager transactionManager,
//                           ItemReader<Vaccine> vaccineReader, ItemProcessor<Vaccine, Notification> checkVaccineProcessor,
//                           ItemWriter<Notification> notificationWriter) {
//        return new StepBuilder("getVaccine", jobRepository)
//                .<Vaccine, Notification>chunk(CHUNK_SIZE, transactionManager)
//                .reader(vaccineReader)
//                .processor(checkVaccineProcessor)
//                .writer(notificationWriter)
//                .build();
//    }
//    @Bean
//    public Step getLocations() {
//        return null;
//    }
//    @Bean
//    public Step setLocations() {
//        return null;
//    }
//
//}
