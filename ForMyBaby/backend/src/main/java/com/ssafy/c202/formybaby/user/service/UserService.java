package com.ssafy.c202.formybaby.user.service;

import com.ssafy.c202.formybaby.user.dto.request.UserUpdateRequest;
import com.ssafy.c202.formybaby.user.dto.response.UserProfileResponse;
import com.ssafy.c202.formybaby.user.dto.response.UserReadResponse;
import com.ssafy.c202.formybaby.user.entity.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
   UserReadResponse findUser(Long userId);
   User findByOauthId(Long oauthId);
   User registerUser(User user);

   //void deleteUser(String token);
   void changeBaby(String token, Long babyId);
   void deleteUser(Long userId);
   UserProfileResponse updateUser(UserUpdateRequest userUpdateRequest);
   String findFcmToken(Long userId);
   void setFCMToken(Long userId, String fcmToken);
   void setLocation(Long userId, Double lat, Double lon);
}
