package com.ssafy.c202.formybaby.user.service;

import java.util.Map;

public interface OAuthService {
    Map<String, Object> getKakaoUserInfo(String accessToken);
    String processUserLoginOrRegistration(Map<String, Object> kakaoUserInfo);
}
