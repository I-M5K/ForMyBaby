package com.ssafy.c202.formybaby.stamp.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Entity
@Data
public class Stamp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private long stampId;

    @Column(nullable = false)
    private long babyId;

    @Column(nullable = false)
    private long step;

    @Column(nullable = false)
    private String stampImg;

    @Column(nullable = true)
    private String memo;

    @Column(nullable = false)
    private Timestamp createdAt;
}
