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
    private String healthTitle;

    @Column(nullable = false)
    private int startAt;

    @Column(nullable = false)
    private int endAt;


}
