package com.ssafy.c202.formybaby.user.entity;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Oauth {

    @Id
    @Column(nullable = false)
    private Long oauthId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String profileImg;

    @Column(nullable = false)
    private String accountEmail;
}
