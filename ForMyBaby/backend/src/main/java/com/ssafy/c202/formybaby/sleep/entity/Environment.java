package com.ssafy.c202.formybaby.sleep.entity;

import com.ssafy.c202.formybaby.baby.entity.Baby;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Generated;

import java.sql.Timestamp;

@Data
@Entity
public class Environment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long environmentId;

    @ManyToOne
    @JoinColumn(name = "baby_id")
    private Baby baby;

    @Column(nullable = false)
    private float temperature;

    @Column(nullable = false)
    private float humidity;

    @Column(nullable = false)
    private Timestamp createdAt;
}
