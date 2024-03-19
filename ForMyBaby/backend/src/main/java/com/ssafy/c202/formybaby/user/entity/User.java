package com.ssafy.c202.formybaby.user.entity;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class User {

    @Id
    private Long userId;

    @OneToOne
    @JoinColumn(name = "oauth_id")
    private Oauth oauth;

    @Column(nullable = false)
    private boolean isDanger;

    @Column(nullable = false)
    private boolean isGeneral;

    @Column(nullable = false)
    private boolean sound;

    @Column(nullable = false)
    private boolean userState;

}