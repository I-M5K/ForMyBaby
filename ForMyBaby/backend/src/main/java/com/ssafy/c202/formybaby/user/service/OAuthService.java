package com.ssafy.c202.formybaby.user.service;

import com.ssafy.c202.formybaby.user.entity.Oauth;

import java.util.Map;

public interface OAuthService {
    Oauth  findByOauthId(Long oauthId);
    Map<String, Object> getKakaoUserInfo(String accessToken);
    String processUserLogin(Map<String, Object> kakaoUserInfo);
    String processUserRegistration(Map<String, Object> kakaoUserInfo);
}
