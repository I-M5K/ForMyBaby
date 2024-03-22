package com.ssafy.c202.formybaby.user.service;

import com.ssafy.c202.formybaby.user.dto.response.UserReadResponse;
import com.ssafy.c202.formybaby.user.entity.User;
import org.springframework.http.ResponseEntity;

public interface UserService {
   ResponseEntity<UserReadResponse> findByUserId(Long id);
   User findByOauthId(Long oauthId);
   User registerUser(User user);
   void deleteUser(Long userId);
}
