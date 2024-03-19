package com.ssafy.c202.formybaby.notification.repository;

import com.ssafy.c202.formybaby.notification.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findAllByUserUserId(Long userId);

    void deleteByNotificationId(Long notificationId);
}
