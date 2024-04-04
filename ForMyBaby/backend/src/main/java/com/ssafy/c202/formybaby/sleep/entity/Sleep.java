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

    @Column(nullable = true)
    private Timestamp createdAt;

    @Column(nullable = true)
    private Timestamp endAt;

    @Column(nullable = true)
    private int sleepCnt;

    @Column(nullable = true)
    private int sleepTime;
}
