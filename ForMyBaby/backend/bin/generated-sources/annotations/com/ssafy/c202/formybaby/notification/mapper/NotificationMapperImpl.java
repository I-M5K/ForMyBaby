package com.ssafy.c202.formybaby.notification.mapper;

import com.ssafy.c202.formybaby.global.jpaEnum.NotificationType;
import com.ssafy.c202.formybaby.notification.entity.Notification;
import com.ssafy.c202.formybaby.user.entity.Family;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-04-04T17:13:59+0900",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.38.0.v20240325-1403, environment: Java 17.0.10 (Eclipse Adoptium)"
)
@Component
public class NotificationMapperImpl implements NotificationMapper {

    @Override
    public Notification fromBatch(Family family, NotificationType notificationType, String title, String content, boolean isChecked) {
        if ( family == null && notificationType == null && title == null && content == null ) {
            return null;
        }

        Notification notification = new Notification();

        if ( family != null ) {
            notification.setBaby( family.getBaby() );
            notification.setUser( family.getUser() );
        }
        notification.setNotificationType( notificationType );
        notification.setTitle( title );
        notification.setContent( content );
        notification.setIsChecked( isChecked );

        return notification;
    }
}
