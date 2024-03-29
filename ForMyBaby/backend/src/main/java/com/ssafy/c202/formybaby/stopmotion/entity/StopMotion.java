package com.ssafy.c202.formybaby.stopmotion.entity;

import com.ssafy.c202.formybaby.baby.entity.Baby;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Data
public class Stopmotion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private Long motionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "baby_id", nullable = false)
    private Baby baby;

    // S3 주소
    @Column(nullable = false)
    private String motionUrl;

    // 생성 날짜
    @Column(nullable = false)
    private Timestamp createdAt;
}
