package com.ssafy.c202.formybaby.sleep.entity;

import com.ssafy.c202.formybaby.baby.entity.Baby;
import com.ssafy.c202.formybaby.global.jpaEnum.SleepCategory;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
public class Sleep {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long sleepId;

    @ManyToOne
    @JoinColumn(name = "baby_id")
    private Baby baby;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SleepCategory sleepCategory;

    @Column(nullable = false)
    private Timestamp createdAt;

    @Column(nullable = false)
    private Timestamp endAt;

    @Column(nullable = false)
    private int sleepCnt;

    @Column(nullable = false)
    private int sleepTime;
}
