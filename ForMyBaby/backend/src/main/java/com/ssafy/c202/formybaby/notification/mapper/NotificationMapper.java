package com.ssafy.c202.formybaby.notification.mapper;

import com.ssafy.c202.formybaby.global.jpaEnum.NotificationType;
import com.ssafy.c202.formybaby.notification.entity.Notification;
import com.ssafy.c202.formybaby.user.entity.Family;
import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring")
public interface NotificationMapper {
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.SET_TO_DEFAULT)
    Notification fromBatch(Family family, NotificationType notificationType, String title, String content, boolean isChecked);
}
