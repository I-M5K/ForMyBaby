//package com.ssafy.c202.formybaby.fcm.batch;
//
//import com.ssafy.c202.formybaby.notification.entity.Notification;
//import lombok.RequiredArgsConstructor;
//import org.springframework.batch.core.Job;
//import org.springframework.batch.core.Step;
//import org.springframework.batch.core.configuration.annotation.JobScope;
//import org.springframework.batch.core.job.builder.JobBuilder;
//import org.springframework.batch.core.launch.support.RunIdIncrementer;
//import org.springframework.batch.core.repository.JobRepository;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//@Configuration
//@RequiredArgsConstructor
//public class JobConfig {
//    public static final Integer CHUNK_SIZE = 100;
//
//    @JobScope
//    @Bean
//    public Job createHealth(JobRepository jobRepository, Step getBabies, Step getHealth) {
//        return new JobBuilder("createGeneral", jobRepository)
//                .incrementer(new RunIdIncrementer())
//                .start(getBabies)
//                .next(getHealth)
//                .build();
//    }
//
//    @JobScope
//    @Bean
//    public Job createVaccine(JobRepository jobRepository, Step getBabies, Step getVaccine) {
//        return new JobBuilder("checkPriority", jobRepository)
//                .incrementer(new RunIdIncrementer())
//                .start(getBabies)
//                .next(getVaccine)
//                .build();
//    }
//
//    @JobScope
//    @Bean
//    public Job checkPriority(JobRepository jobRepository, Step getLocations, Step setLocations) {
//        return new JobBuilder("checkPriority", jobRepository)
//                .incrementer(new RunIdIncrementer())
//                .start(getLocations)
//                .next(setLocations)
//                .build();
//    }
//
//}
