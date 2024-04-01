package com.ssafy.c202.formybaby.notification.controller;

import com.amazonaws.Response;
import com.google.api.Http;
import com.ssafy.c202.formybaby.global.redis.RedisService;
import com.ssafy.c202.formybaby.notification.dto.request.SettingUpdateRequest;
import com.ssafy.c202.formybaby.notification.dto.response.NotificationReadResponse;
import com.ssafy.c202.formybaby.notification.dto.response.SettingReadResponse;
import com.ssafy.c202.formybaby.notification.entity.Notification;
import com.ssafy.c202.formybaby.notification.repository.NotificationRepository;
import com.ssafy.c202.formybaby.notification.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/notification")
@CrossOrigin(origins = "http://localhost:3000")
public class NotificationController {

    private final NotificationService notificationService;
    private final RedisService redisService;

    @GetMapping("/list")
    public ResponseEntity<List<NotificationReadResponse>> getNotificationList (@RequestHeader(name="Authorization") String token) {
        String userId = redisService.getUserIdByToken(token);
        return new ResponseEntity<>(notificationService.getList(Long.valueOf(userId)), HttpStatus.OK);
    }

    @DeleteMapping("{notificationId}")
    public ResponseEntity<?> deleteNotification (@PathVariable Long notificationId) {
        notificationService.delete(notificationId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/deleteall")
    public ResponseEntity<?> deleteAllNotification (@RequestHeader(name="Authorization") String token) {
        String userId = redisService.getUserIdByToken(token);
        Long babyId = Long.valueOf(redisService.getBabyIdByToken(userId));
        notificationService.deleteAll(Long.valueOf(userId));
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PatchMapping("/check/{notificationId}")
    public ResponseEntity<?> checkNotification(@RequestHeader(name="Authorization") String token,
                                               @PathVariable Long notificationId) {
        notificationService.checkNotification(notificationId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/setting")
    public ResponseEntity<SettingReadResponse> getSetting(@RequestHeader(name="Authorization") String token) {
        return new ResponseEntity<>(notificationService.getSetting(Long.valueOf(redisService.getUserIdByToken(token))), HttpStatus.OK);
    }

    @PatchMapping("/setting")
    public ResponseEntity<SettingReadResponse> updateSetting(@RequestHeader(name="Authorization") String token,
                                                             @RequestBody SettingUpdateRequest settingUpdateRequest) {
        System.out.println(redisService.getUserIdByToken(token));
        Long userId = Long.valueOf(redisService.getUserIdByToken(token));
        return new ResponseEntity<>(notificationService.updateSetting(settingUpdateRequest, userId), HttpStatus.OK);
    }

    @PatchMapping("/check/all")
    public ResponseEntity<?> checkAll(@RequestHeader(name="Authorization") String token) {
        String userId = redisService.getUserIdByToken(token);
        Long babyId = Long.valueOf(redisService.getBabyIdByToken(userId));
        notificationService.checkAll(Long.valueOf(userId), babyId);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
