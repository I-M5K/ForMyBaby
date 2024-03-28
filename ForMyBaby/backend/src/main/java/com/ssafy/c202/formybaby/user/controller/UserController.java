package com.ssafy.c202.formybaby.user.controller;


import com.ssafy.c202.formybaby.global.redis.LatLon;
import com.ssafy.c202.formybaby.global.redis.RedisService;
import com.ssafy.c202.formybaby.user.dto.request.UserUpdateRequest;
import com.ssafy.c202.formybaby.user.dto.response.UserProfileResponse;
import com.ssafy.c202.formybaby.user.dto.response.UserReadResponse;
import com.ssafy.c202.formybaby.user.entity.User;
import com.ssafy.c202.formybaby.user.exception.NotFoundException;
import com.ssafy.c202.formybaby.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/v1/users")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private RedisService redisService;
    @GetMapping()
    public ResponseEntity<UserReadResponse> findUser(@RequestParam("userId") Long userId){
        try {
            UserReadResponse userReadResponse = userService.findUser(userId);
            return new ResponseEntity<>(userReadResponse, HttpStatus.OK);
        }catch (NotFoundException e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/select")
    public ResponseEntity<?> changeBaby(@RequestHeader(name = "Authorization") String token, @RequestParam Long babyId) {
        try {
            userService.changeBaby(token, babyId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping
    public ResponseEntity<UserProfileResponse> updateUser(@RequestBody UserUpdateRequest userUpdateRequest){
        try {
            UserProfileResponse userProfileResponse = userService.updateUser(userUpdateRequest);
            return new ResponseEntity<>(userProfileResponse, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

//    @DeleteMapping()
//    public ResponseEntity<?> deleteUser(@RequestHeader(name = "Authorization") String token){
//        try{
//            userService.deleteUser(token);
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }catch (Exception e){
//            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
//        }
//    }

    @DeleteMapping()
    public ResponseEntity<?> deleteUser(@RequestParam("userId") Long userId){
        try{
            userService.deleteUser(userId);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/location")
    public ResponseEntity<String> receiveLocation(@RequestHeader(name="Authorization") String token,
                                                  @RequestBody Map<String, Object> locationData) {
        // 받은 데이터 처리
        Double lat = (Double) locationData.get("latitude");
        Double lon = (Double) locationData.get("longitude");
        log.info("Token : {}", token);
        userService.setLocation(Long.valueOf(redisService.getUserIdByToken(token)), lat, lon);

        // 작업이 완료되면 클라이언트에 성공 상태 응답을 반환
        return ResponseEntity.status(HttpStatus.OK).body("Location data received successfully");
    }
    @PatchMapping("/fcm")
    public ResponseEntity<String> updateFCMToken(@RequestHeader(name="Authorization") String token,
                                                 @RequestBody Map<String, Object> data) {
        String fcmToken = (String)data.get("fcmToken");
        log.info("FCMToken : {}", fcmToken);
        if (fcmToken != null) {
            userService.setFCMToken(Long.valueOf(redisService.getUserIdByToken(token)), fcmToken);
            return ResponseEntity.ok("FCM token update successful");
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to update FCM token");
        }
    }
}
