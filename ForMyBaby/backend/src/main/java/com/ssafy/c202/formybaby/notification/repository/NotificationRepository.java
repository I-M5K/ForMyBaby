package com.ssafy.c202.formybaby.notification.repository;

import com.ssafy.c202.formybaby.notification.dto.request.SettingUpdateRequest;
import com.ssafy.c202.formybaby.notification.dto.response.NotificationReadResponse;
import com.ssafy.c202.formybaby.notification.dto.response.SettingReadResponse;
import com.ssafy.c202.formybaby.notification.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findAllByUserUserId(Long userId);

    @Query("SELECT n.notificationId, n.notificationType, n.title, n.content, n.isChecked, n.createdAt FROM Notification n WHERE n.user.userId=:userId AND n.baby.babyId=:babyId")
    List<NotificationReadResponse> findListByUserId(Long userId, Long babyId);

    @Query("SELECT n " +
            "FROM Notification n " +
            "WHERE n.user.userId = :userId AND n.baby.babyId = :babyId AND n.isChecked=false")
    List<Notification> findAllUncheckedByUserId(Long userId, Long babyId);

    @Query("SELECT COUNT(n) " +
            "FROM Notification n " +
            "WHERE n.user.userId = :userId AND n.baby.babyId=:babyId AND n.isChecked=false")
    int findUncheckedCountByUserId(Long userId, Long babyId);

    @Query("SELECT COUNT(n) FROM Notification n WHERE n.user.userId = :userId AND n.baby.babyId = :babyId")
    int findAllCntByUserId(Long userId, Long babyId);

    @Query("SELECT u.isGeneral, u.isDanger, u.isSound from User u where u.userId=:userId")
    SettingReadResponse findSettingByUserId(Long userId);
    @Query("UPDATE Notification n SET n.isChecked = TRUE WHERE n.notificationId = :notificationId")
    void checkNotification(Long notificationId);
    @Query("UPDATE User u SET u.isDanger=:isDanger, u.isGeneral=:isGeneral, u.isSound=:isSound WHERE u.userId=:userId")
    SettingReadResponse updateSettingByUserId(Boolean isGeneral,
                                              Boolean isDanger,
                                              Boolean isSound,
                                              Long userId);

    void deleteByNotificationId(Long notificationId);
    @Query("DELETE FROM Notification n WHERE n.user.userId = :userId AND n.baby.babyId = :babyId")
    void deleteAllByUserId(Long userId, Long babyId);
}
