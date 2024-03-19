package com.ssafy.c202.formybaby.user.entity;


import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import java.util.List;
@Data
@Entity
public class User {

    @Id
    private Long userId;

    @OneToOne
    @JoinColumn(name = "oauth_id")
    private Oauth oauth;

    @Column(nullable = false)
    @ColumnDefault("true")
    private boolean isDanger;

    @Column(nullable = false)
    @ColumnDefault("true")
    private boolean isGeneral;

    @Column(nullable = false)
    @ColumnDefault("true")
    private boolean sound;

    private String fcmToken;

    @Column(nullable = false)
    @ColumnDefault("false")
    private boolean userState;
}
