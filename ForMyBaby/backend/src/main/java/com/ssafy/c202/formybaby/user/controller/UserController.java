package com.ssafy.c202.formybaby.user.controller;


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

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/v1/users")

public class UserController {

    @Autowired
    private UserService userService;
    @GetMapping()
    public ResponseEntity<UserReadResponse> findUser(@RequestParam("userId") Long userId){
        try {
            UserReadResponse userReadResponse = userService.findByUserReadResponseUserId(userId).getBody();
            log.info("userReadResponse : " + userReadResponse);
            return new ResponseEntity<>(userReadResponse, HttpStatus.OK);
        }catch (NotFoundException e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
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
    @DeleteMapping()
    public ResponseEntity<?> deleteUser(@RequestHeader(name = "Authorization") String token){
        try{
            userService.deleteUser(token);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
