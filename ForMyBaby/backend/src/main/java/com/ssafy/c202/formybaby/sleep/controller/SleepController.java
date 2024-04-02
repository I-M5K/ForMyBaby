package com.ssafy.c202.formybaby.sleep.controller;

import com.ssafy.c202.formybaby.sleep.dto.request.AwakeCreateRequest;
import com.ssafy.c202.formybaby.sleep.dto.request.SleepOnCreateRequest;
import com.ssafy.c202.formybaby.sleep.dto.response.SleepTodayAllList;
import com.ssafy.c202.formybaby.sleep.dto.response.SleepWeekAllList;
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
@CrossOrigin(origins = "https://j10c202.p.ssafy.io")
public class SleepController {

    private final SleepService sleepService;

    @GetMapping()
    public ResponseEntity<Void> getSleepOnTime(@RequestHeader(name = "Authorization") String token, @RequestParam Long babyId) {
        log.info("getSleepOnTime");
        sleepService.getSleepOnTime(token,babyId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/today")
    public ResponseEntity<SleepTodayAllList> getTodayAllList(@RequestHeader(name = "Authorization") String token){
        try{
            SleepTodayAllList sleepTodayAllList = sleepService.getTodayAllList(token);
            return new ResponseEntity<>(sleepTodayAllList,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/awake")
    public ResponseEntity<?> getAwakeOnTime(@RequestHeader(name = "Authorization") String token, @RequestParam Long babyId){
        log.info("getAwakeOnTime");
        sleepService.getAwakeOnTime(token,babyId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/week")
    public ResponseEntity<SleepWeekAllList> getWeekAllList(@RequestHeader(name = "Authorization") String token,  @RequestParam Long endAt){
        try{
            Timestamp endTime = new Timestamp(endAt);
            return new ResponseEntity<>(sleepService.getWeekAllList(token, endTime),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    };
}
