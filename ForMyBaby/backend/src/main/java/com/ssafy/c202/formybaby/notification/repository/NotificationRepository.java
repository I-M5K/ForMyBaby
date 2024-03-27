package com.ssafy.c202.formybaby.notification.repository;

import com.ssafy.c202.formybaby.notification.dto.request.SettingUpdateRequest;
import com.ssafy.c202.formybaby.notification.dto.response.SettingReadResponse;
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
            "WHERE n.user.userId = :userId AND nc.isChecked=false")
    List<Notification> findAllUncheckedByUserId(Long userId);

    @Query("SELECT COUNT(nc) " +
            "FROM NotificationCheck nc " +
            "JOIN nc.notification n " +
            "WHERE n.user.userId = :userId AND nc.isChecked=false")
    int findUncheckedCountByUserId(Long userId);

    @Query("SELECT COUNT(n) FROM Notification n WHERE n.user.userId = :userId")
    int findAllCntByUserId(Long userId);

    @Query("SELECT u.isGeneral, u.isDanger, u.isSound from User u where u.userId=:userId")
    SettingReadResponse findSettingByUserId(Long userId);
    @Query("UPDATE NotificationCheck nc SET nc.isChecked = TRUE WHERE nc.notification.notificationId = :notificationId")
    void checkNotification(Long notificationId);
    @Query("UPDATE User u SET u.isDanger=:isDanger, u.isGeneral=:isGeneral, u.isSound=:isSound WHERE u.userId=:userId")
    SettingReadResponse updateSettingByUserId(Boolean isGeneral,
                                              Boolean isDanger,
                                              Boolean isSound,
                                              Long userId);

    void deleteByNotificationId(Long notificationId);
    @Query("DELETE FROM Notification n WHERE n.user.userId = : userId")
    void deleteAllByUserId(Long userId);
}
