package com.ssafy.c202.formybaby.fcm.entity;

import com.ssafy.c202.formybaby.user.entity.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FCMMessage {

    private String fcmToken;

    private String title;

    private String body;

    private String topic;

    private String type;

    private String babyId;

}
