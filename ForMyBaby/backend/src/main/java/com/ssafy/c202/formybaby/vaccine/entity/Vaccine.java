package com.ssafy.c202.formybaby.vaccine.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Vaccine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int vaccineId;

    @Column(nullable = false)
    private String target;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private int startAt;

    @Column(nullable = false)
    private int endAt;
}
