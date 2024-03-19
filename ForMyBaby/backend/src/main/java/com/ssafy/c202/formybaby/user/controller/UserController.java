package com.ssafy.c202.formybaby.user.controller;


import com.ssafy.c202.formybaby.user.entity.User;
import com.ssafy.c202.formybaby.user.exception.NotFoundException;
import com.ssafy.c202.formybaby.user.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/v1/users")

public class UserController {

    @Autowired
    private UserService userService;
    @GetMapping()
    public ResponseEntity<User> findUser(@RequestParam("userId") Long userId){
        try {
            User userReadResponse = userService.findByUserId(userId);
            log.info("userReadResponse : " + userReadResponse);
            return new ResponseEntity<>(userReadResponse, HttpStatus.OK);
        }catch (NotFoundException e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
