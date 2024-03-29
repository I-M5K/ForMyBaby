package com.ssafy.c202.formybaby.notification.repository;

import com.ssafy.c202.formybaby.global.jpaEnum.NotificationType;
import com.ssafy.c202.formybaby.notification.dto.request.SettingUpdateRequest;
import com.ssafy.c202.formybaby.notification.dto.response.NotificationReadResponse;
import com.ssafy.c202.formybaby.notification.dto.response.SettingReadResponse;
import com.ssafy.c202.formybaby.notification.entity.Notification;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface NotificationRepository extends JpaRepository<Notification, Long> {

    List<Notification> findAllByUserUserId(Long userId);

    @Query("SELECT NEW com.ssafy.c202.formybaby.notification.dto.response.NotificationReadResponse(" +
            "n.notificationId," +
            "n.user.userId, " +
            "n.baby.babyId," +
            "n.notificationType," +
            "n.title," +
            "n.content," +
            "n.isChecked," +
            "n.createdAt" +
            ")" +
            "FROM Notification n WHERE n.user.userId=:userId")
    List<NotificationReadResponse> findListByUserId(Long userId);

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

    @Query("SELECT new com.ssafy.c202.formybaby.notification.dto.response.SettingReadResponse(" +
            "u.isGeneral, u.isDanger, u.isSound)  FROM User u WHERE u.userId=:userId")
    SettingReadResponse findSettingByUserId(Long userId);
    @Modifying
    @Transactional
    @Query("UPDATE Notification n SET n.isChecked = TRUE WHERE n.notificationId = :notificationId")
    void checkNotification(Long notificationId);
    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.isDanger=:isDanger, u.isGeneral=:isGeneral, u.isSound=:isSound WHERE u.userId=:userId")
    void updateSettingByUserId(Boolean isGeneral,
                                              Boolean isDanger,
                                              Boolean isSound,
                                              Long userId);

    void deleteNotificationByNotificationId(Long notificationId);
    @Modifying
    @Transactional
    @Query("DELETE FROM Notification n WHERE n.user.userId = :userId AND n.baby.babyId = :babyId")
    void deleteAllByUserId(Long userId, Long babyId);

    @Modifying
    @Transactional
    @Query("DELETE FROM Notification n WHERE n.baby.babyId=:babyId")
    void deleteAllByBabyId(Long babyId);

    @Modifying
    @Transactional
    @Query("UPDATE Notification n SET n.isChecked=true WHERE n.user.userId=:userId AND n.baby.babyId=:babyId")
    void checkAll(Long userId, Long babyId);
}
