package com.ssafy.c202.formybaby.user.entity;

import com.ssafy.c202.formybaby.baby.entity.Baby;
import com.ssafy.c202.formybaby.global.jpaEnum.Role;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

@Data
@Entity
public class Family {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long familyId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "baby_id")
    private Baby baby;

    @Column(nullable = false)
    private String familyCode;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role = Role.Mother;

    @Column(nullable = false)
    @ColumnDefault("1")
    private int familyRank;


    public boolean isCorrectCode (String familyCode) {
        return this.familyCode.equals(familyCode);
    }
}
