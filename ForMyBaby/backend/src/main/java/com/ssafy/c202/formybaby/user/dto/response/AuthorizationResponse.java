package com.ssafy.c202.formybaby.user.dto.response;

import java.util.List;

public record AuthorizationResponse (
        Long userId,

        Long oauthId,

        String name,

        String accountEmail,

        List<String> profileImg,

        String jwtToken,

        String fcmToken
) { }
