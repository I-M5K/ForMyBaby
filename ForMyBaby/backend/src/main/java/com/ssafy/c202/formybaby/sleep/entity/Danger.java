package com.ssafy.c202.formybaby.sleep.entity;

import com.ssafy.c202.formybaby.baby.entity.Baby;
import com.ssafy.c202.formybaby.global.jpaEnum.DangerType;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
public class Danger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dangerId;

    @ManyToOne
    @JoinColumn(name = "baby_id")
    private Baby baby;

    @Column(nullable = false)
    private Timestamp createdAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private DangerType dangerType;

    @Column(nullable = false)
    private int dangerCnt;


}
