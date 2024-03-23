package com.ssafy.c202.formybaby.user.dto.response;

public record AuthorizationResponse (
        Long userId,

        Long oauthId,

        String name,

        String accountEmail,

        String profileImg,

        String jwtToken,

        String fcmToken
) { }
