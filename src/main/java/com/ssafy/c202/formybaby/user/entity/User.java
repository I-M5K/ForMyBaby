package com.ssafy.c202.formybaby.user.entity;


import jakarta.persistence.*;
import lombok.Data;
import java.sql.Timestamp;

@Data
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false)
    private String userEmail;

    @Column(nullable = false)
    private String profile_img;

    @Column(nullable = false)
    private String provider;

    @Column(nullable = false)
    private String role;

    @Column(nullable = false)
    private int lank;

    @Column(nullable = false)
    private boolean isDanger;

    @Column(nullable = false)
    private boolean isGeneral;

    @Column(nullable = false)
    private boolean sound;

    @Column(nullable = false)
    private String fcmToken;

    @Column(nullable = false)
    private String familyCode;

    @Column(nullable = false)
    private Timestamp createdAt;

    @Column(nullable = false)
    private Timestamp updatedAt;

    private Timestamp deletedAt;



}
