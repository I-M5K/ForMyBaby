//package com.ssafy.c202.formybaby.fcm.batch;
//
//import com.ssafy.c202.formybaby.baby.entity.Baby;
//import com.ssafy.c202.formybaby.health.entity.Health;
//import com.ssafy.c202.formybaby.vaccine.entity.Vaccine;
//import jakarta.persistence.EntityManagerFactory;
//import lombok.AllArgsConstructor;
//import lombok.RequiredArgsConstructor;
//import org.springframework.batch.core.configuration.annotation.StepScope;
//import org.springframework.batch.item.database.JpaPagingItemReader;
//import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.time.LocalDate;
//import java.time.format.DateTimeFormatter;
//import java.util.Collections;
//
//@Configuration
//@RequiredArgsConstructor
//public class ItemReaderConfig {
//
//    private final EntityManagerFactory emf;
//
//    @Autowired
//    private DataShareBean<Baby> babyShareBean;
//    @Autowired
//    private DataShareBean<Health> healthShareBean;
//    @Autowired
//    private DataShareBean<Vaccine> vaccineShareBean;
//
//
//    @Bean
//    @StepScope
//    public JpaPagingItemReader<Baby> babyReader() {
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//        LocalDate sixYearsAgo = LocalDate.now().minusYears(6);
//        String formattedDate = sixYearsAgo.format(formatter);
//
//        String query = "SELECT b FROM Baby b WHERE b.birthDate >= :startDate";
//        return new JpaPagingItemReaderBuilder<Baby>()
//                .entityManagerFactory(emf)
//                .queryString(query)
//                .parameterValues(Collections.singletonMap("startDate", formattedDate))
//                .pageSize(StepConfig.CHUNK_SIZE)
//                .name("babyReader")
//                .build();
//    }
//
//    @Bean
//    @StepScope
//    public JpaPagingItemReader<Health> healthReader() {
//        String query = ("SELECT h FROM Health h");
//        return new JpaPagingItemReaderBuilder<Health>()
//                .entityManagerFactory(emf)
//                .queryString(query)
//                .pageSize(StepConfig.CHUNK_SIZE)
//                .name("healthReader")
//                .build();
//    }
//
//    @Bean
//    @StepScope
//    public JpaPagingItemReader<Vaccine> vaccineReader() {
//        String query = ("SELECT v FROM Vaccine v");
//        return new JpaPagingItemReaderBuilder<Vaccine>()
//                .entityManagerFactory(emf)
//                .queryString(query)
//                .pageSize(StepConfig.CHUNK_SIZE)
//                .name("vaccineReader")
//                .build();
//    }
//}
