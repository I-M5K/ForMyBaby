package com.ssafy.c202.formybaby.fcm.batch;

import com.ssafy.c202.formybaby.health.entity.Health;
import com.ssafy.c202.formybaby.user.entity.Family;
import com.ssafy.c202.formybaby.vaccine.entity.Vaccine;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;

@Configuration
@RequiredArgsConstructor
public class ItemReaderConfig {

    private final EntityManagerFactory emf;
    private final DataShareBean<Family> generalShareBean;


    @Bean
    @StepScope
    public JpaPagingItemReader<Family> generalFamilyReader() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate limit = LocalDate.now().minusYears(13);
        LocalDate formattedDate = LocalDate.parse(limit.format(formatter));
        generalShareBean.map().clear();
        String query = "SELECT f FROM Family f JOIN FETCH f.user u JOIN FETCH f.baby b " +
                "WHERE b.birthDate >= :startDate AND u.isGeneral = true";
        return new JpaPagingItemReaderBuilder<Family>()
                .entityManagerFactory(emf)
                .queryString(query)
                .parameterValues(Collections.singletonMap("startDate", formattedDate))
                .pageSize(StepConfig.CHUNK_SIZE)
                .name("familyReader")
                .build();
    }

    @Bean
    @StepScope
    public JpaPagingItemReader<Health> healthReader() {
        String query = ("SELECT h FROM Health h");
        return new JpaPagingItemReaderBuilder<Health>()
                .entityManagerFactory(emf)
                .queryString(query)
                .pageSize(StepConfig.CHUNK_SIZE)
                .name("healthReader")
                .build();
    }

    @Bean
    @StepScope
    public JpaPagingItemReader<Vaccine> vaccineReader() {
        String query = ("SELECT v FROM Vaccine v");
        return new JpaPagingItemReaderBuilder<Vaccine>()
                .entityManagerFactory(emf)
                .queryString(query)
                .pageSize(StepConfig.CHUNK_SIZE)
                .name("vaccineReader")
                .build();
    }
}
