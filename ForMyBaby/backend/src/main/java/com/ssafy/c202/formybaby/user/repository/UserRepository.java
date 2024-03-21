package com.ssafy.c202.formybaby.user.repository;

import com.ssafy.c202.formybaby.user.entity.Oauth;
import com.ssafy.c202.formybaby.user.entity.User;
import com.ssafy.c202.formybaby.user.mapper.UserInfoMapper;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
<<<<<<< HEAD
    Oauth findByOauth_OauthId(Long oauthId);
=======
    Optional<UserInfoMapper> findUserInfoByOauth_OauthId(Long oauthId);

>>>>>>> feat/#628-backend-user
    User findByUserId(Long userId);
}
