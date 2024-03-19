package com.ssafy.c202.formybaby.notification.entity;

import com.ssafy.c202.formybaby.baby.entity.Baby;
import com.ssafy.c202.formybaby.user.entity.User;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class NotificationCheck {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int checkID;

    @ManyToOne
    @JoinColumn(name = "notificatin_id")
    private Notification notification;

    @ManyToOne
    @JoinColumn(name = "baby_id")
    private Baby baby;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private boolean isChecked;

}
