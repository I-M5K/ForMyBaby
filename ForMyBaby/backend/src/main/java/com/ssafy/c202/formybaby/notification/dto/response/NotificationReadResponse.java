package com.ssafy.c202.formybaby.notification.dto.response;

import com.ssafy.c202.formybaby.global.jpaEnum.NotificationType;

import java.sql.Timestamp;
import java.time.LocalDate;

public record NotificationReadResponse(
        Long notificationId,
        NotificationType notificationType,
        String title,
        String content,
        Boolean isChecked,
        Timestamp createdAt
) {
}
