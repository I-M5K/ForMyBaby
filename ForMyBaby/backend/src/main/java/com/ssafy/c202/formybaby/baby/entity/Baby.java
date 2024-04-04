package com.ssafy.c202.formybaby.baby.entity;

import com.ssafy.c202.formybaby.global.jpaEnum.BabyGender;
import com.ssafy.c202.formybaby.global.jpaEnum.Role;
import com.ssafy.c202.formybaby.user.entity.Family;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

@Data
@Entity
public class Baby implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long babyId;

    @Column(nullable = false)
    private String babyName;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BabyGender babyGender;

    private String profileImg;

    public void setRole(Role role) {
    }
}
