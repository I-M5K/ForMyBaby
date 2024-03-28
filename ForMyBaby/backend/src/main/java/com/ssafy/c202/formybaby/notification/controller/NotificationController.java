package com.ssafy.c202.formybaby.notification.controller;

import com.ssafy.c202.formybaby.global.redis.RedisService;
import com.ssafy.c202.formybaby.notification.dto.response.NotificationReadResponse;
import com.ssafy.c202.formybaby.notification.dto.response.SettingReadResponse;
import com.ssafy.c202.formybaby.notification.entity.Notification;
import com.ssafy.c202.formybaby.notification.repository.NotificationRepository;
import com.ssafy.c202.formybaby.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/notification")
@CrossOrigin(origins = "http://localhost:3000")
public class NotificationController {

    private final NotificationService notificationService;
    private final RedisService redisService;

    @GetMapping("/list")
    public ResponseEntity<List<NotificationReadResponse>> getNotificationList (@RequestHeader(name="Authorization") String token) {
        Long userId = Long.valueOf(redisService.getUserIdByToken(token));
        Long babyId = Long.valueOf(redisService.getBabyIdByToken(token));

        return new ResponseEntity<>(notificationService.getList(userId, babyId), HttpStatus.OK);
    }

    @DeleteMapping("{notificationId}")
    public ResponseEntity<?> deleteNotification (Long notificationId) {
        notificationService.delete(notificationId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/deleteall")
    public ResponseEntity<?> deleteAllNotification (@RequestHeader(name="Authorization") String token) {
        Long userId = Long.valueOf(redisService.getUserIdByToken(token));
        Long babyId = Long.valueOf(redisService.getBabyIdByToken(token));
        notificationService.deleteAll(userId, babyId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/check/{notificationId}")
    public ResponseEntity<?> checkNotification(@RequestHeader(name="Authorization") String token,
                                               @PathVariable Long notificationId) {
        notificationService.checkNotification(notificationId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
//
//    @GetMapping("/setting/{userId}")
//    public ResponseEntity<SettingReadResponse> getSetting
}
