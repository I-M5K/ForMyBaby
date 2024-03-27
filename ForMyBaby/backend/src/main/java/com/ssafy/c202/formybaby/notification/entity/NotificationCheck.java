package com.ssafy.c202.formybaby.notification.entity;

import com.ssafy.c202.formybaby.baby.entity.Baby;
import com.ssafy.c202.formybaby.user.entity.User;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

@Data
@Entity
public class NotificationCheck {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int checkID;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "notification_id")
    private Notification notification;

    @Column(nullable = false)
    @ColumnDefault("false")
    private boolean isChecked;

}
