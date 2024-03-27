package com.ssafy.c202.formybaby.notification.mapper;

import com.ssafy.c202.formybaby.global.jpaEnum.NotificationType;
import com.ssafy.c202.formybaby.notification.entity.Notification;
import com.ssafy.c202.formybaby.user.entity.Family;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface NotificationMapper {
    Notification fromBatch(Family family, NotificationType notificationType, String title, String content);
}
