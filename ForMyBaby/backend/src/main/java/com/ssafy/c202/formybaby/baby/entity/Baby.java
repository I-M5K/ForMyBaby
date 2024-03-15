package com.ssafy.c202.formybaby.baby.entity;

import com.ssafy.c202.formybaby.global.jpaEnum.BabyGender;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

@Data
@Entity
public class Baby {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long babyId;

    @Column(nullable = false)
    private String babyName;

    @Column(nullable = false)
    private Date birthDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BabyGender babyGender;

    private String profileImg;

}
