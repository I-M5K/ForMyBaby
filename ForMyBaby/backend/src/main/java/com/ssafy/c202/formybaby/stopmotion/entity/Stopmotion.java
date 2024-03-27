package com.ssafy.c202.formybaby.stopmotion.entity;

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

    @Column(nullable = false)
    private Long babyId;

    @Column(nullable = false)
    private String motionUrl;

    @Column(nullable = false)
    private Timestamp createdAt;
}
