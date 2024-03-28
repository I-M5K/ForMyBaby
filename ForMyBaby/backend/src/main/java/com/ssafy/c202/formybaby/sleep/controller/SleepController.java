package com.ssafy.c202.formybaby.sleep.controller;

import com.ssafy.c202.formybaby.sleep.service.SleepService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/v1/sleep")
public class SleepController {

    private final SleepService sleepService;

    @GetMapping()
    public ResponseEntity<Void> getSleepOnTime(@RequestHeader(name = "Authorization") String token, @RequestParam Timestamp createdAt) {
        log.info("createdAt : " + createdAt);
        sleepService.getSleepOnTime(token, createdAt);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/awake")
    public ResponseEntity<?> getAwakeTimeList(@RequestHeader(name = "Authorization") String token, @RequestParam Timestamp endAt) {
        log.info("endAt : " + endAt);
        sleepService.getAwakeTimeList(token, endAt);
        return ResponseEntity.ok().build();
    }



}
