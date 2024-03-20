package com.ssafy.c202.formybaby.user.repository;

import com.ssafy.c202.formybaby.user.entity.Oauth;
import com.ssafy.c202.formybaby.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    Oauth findByOauth_OauthId(Long oauthId);
    User findByUserId(Long userId);
}
