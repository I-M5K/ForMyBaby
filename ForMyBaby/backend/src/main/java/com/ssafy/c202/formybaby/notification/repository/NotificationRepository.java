package com.ssafy.c202.formybaby.notification.repository;

import com.ssafy.c202.formybaby.notification.entity.Notification;
import com.ssafy.c202.formybaby.notification.entity.NotificationCheck;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findAllByUserUserId(Long userId);

    @Query("SELECT n " +
            "FROM NotificationCheck nc " +
            "JOIN nc.notification n " +
            "WHERE n.user.userId = :userId")
    List<Notification> findAllUncheckedByUserId(Long userId);

    @Query("SELECT COUNT(nc) " +
            "FROM NotificationCheck nc " +
            "JOIN nc.notification n " +
            "WHERE n.user.userId = :userId")
    int findUncheckedCountByUserId(Long userId);

    @Query("SELECT COUNT(n) FROM Notification n WHERE n.user.userId = :userId")
    int findAllCntByUserId(Long userId);

    void deleteByNotificationId(Long notificationId);
}
