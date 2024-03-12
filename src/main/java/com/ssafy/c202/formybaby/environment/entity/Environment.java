package com.ssafy.c202.formybaby.environment.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
public class Environment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long environmentId;

    @Column(nullable = false)
    private Long babyId;

    @Column(nullable = false)
    private float temperature;

    @Column(nullable = false)
    private float humidity;

    @Column(nullable = false)
    private Timestamp createdAt;
}
