package com.ssafy.c202.formybaby.fcm.batch;

import com.ssafy.c202.formybaby.baby.entity.Baby;
import com.ssafy.c202.formybaby.health.entity.Health;
import com.ssafy.c202.formybaby.vaccine.entity.Vaccine;
import lombok.AllArgsConstructor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.List;

@Configuration
@AllArgsConstructor
public class StepConfig {
    public static final Integer CHUNK_SIZE = 100;

    @Bean
    public Step getBabies(JobRepository jobRepository, PlatformTransactionManager transactionManager,
                          ItemReader<Baby> babyReader, ItemWriter<Baby> babyWriter) {
        return new StepBuilder("getBabies", jobRepository)
                .<Baby, Baby>chunk(CHUNK_SIZE, transactionManager)
                .reader(babyReader)
                .processor(babyWriter)
                .build();
    }
    @Bean
    public Step getHealth(JobRepository jobRepository, PlatformTransactionManager transactionManager,
                          ItemReader<Health> healthReader, ItemWriter<Baby> healthWriter) {
        return new StepBuilder("getHealth", jobRepository)
                .<Health, Health>chunk(CHUNK_SIZE, transactionManager)
                .reader(healthReader)
                .writer(healthWriter)
                .build();

    }
    @Bean
    public Step getVaccine(JobRepository jobRepository, PlatformTransactionManager transactionManager,
                           ItemReader<Health> vaccineReader, ItemWriter<Baby> vaccineWriter) {
        return new StepBuilder("getHealth", jobRepository)
                .<Health, Health>chunk(CHUNK_SIZE, transactionManager)
                .reader(vaccineReader)
                .processor(vaccineWriter)
                .build();
    }
    @Bean
    public Step checkTime() {

    }
    @Bean
    public Step getLocations() {

    }
    @Bean
    public Step locationCheck() {

    }
    @Bean
    public Step setPriority() {

    }
}
