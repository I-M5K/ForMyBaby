package com.ssafy.c202.formybaby.stamp.entity;

import com.ssafy.c202.formybaby.baby.entity.Baby;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Timestamp;


@Entity
@Data
public class Stamp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false)
    private long stampId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "baby_id", nullable = false)
    private Baby baby;

    @Column(nullable = false)
    private long step;

    @Column(nullable = true)
    private String stampImg;

    @Column(nullable = true)
    private String memo;

    @CreationTimestamp
    @Column(nullable = false)
    private String createdAt;
}
