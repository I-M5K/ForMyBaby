package com.ssafy.c202.formybaby.danger.entity;


import jakarta.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
public class Danger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long dangerId;

    @Column(nullable = false)
    private Long babyId;

    @Column(nullable = false)
    private Timestamp createdAt;

    @Column(nullable = false)
    private int dangerType;
}
