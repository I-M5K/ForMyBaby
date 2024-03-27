package com.ssafy.c202.formybaby.user.repository;

import com.ssafy.c202.formybaby.user.entity.Oauth;
import com.ssafy.c202.formybaby.user.entity.User;
import com.ssafy.c202.formybaby.user.mapper.UserInfoMapper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByOauth_OauthId(Long oauthId);
    User findByUserId(Long userId);
    String findFcmTokenByUserId(Long userId);
    @Query("UPDATE User u SET u.fcmToken=:fcmToken WHERE u.userId=:userId")
    void updateFcmTokenByUserId(Long userId, String fcmToken);
}
