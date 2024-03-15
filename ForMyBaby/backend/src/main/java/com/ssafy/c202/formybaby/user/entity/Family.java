package com.ssafy.c202.formybaby.user.entity;

import com.ssafy.c202.formybaby.baby.entity.Baby;
import com.ssafy.c202.formybaby.global.jpaEnum.Role;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Family {
    @Id
    private String familyCode;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "baby_id")
    private Baby baby;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column(nullable = false)
    private int familyRank;

}
