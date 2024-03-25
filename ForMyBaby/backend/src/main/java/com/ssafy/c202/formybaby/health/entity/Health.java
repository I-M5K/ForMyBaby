package com.ssafy.c202.formybaby.health.entity;

import com.ssafy.c202.formybaby.global.jpaEnum.BabyGender;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Data
@Entity
public class Health {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int healthId;

    @Column(nullable = false)
    private String health_title;

    @Column(nullable = false)
    private int start_at;

    @Column(nullable = false)
    private int end_at;

}
