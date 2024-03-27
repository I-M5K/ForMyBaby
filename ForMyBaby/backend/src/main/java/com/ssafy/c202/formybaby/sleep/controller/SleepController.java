package com.ssafy.c202.formybaby.sleep.controller;

import com.ssafy.c202.formybaby.sleep.Dto.response.SleepCntResponse;
import com.ssafy.c202.formybaby.sleep.Dto.response.SleepTimeResponse;
import com.ssafy.c202.formybaby.sleep.service.SleepService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/v1/sleep")
public class SleepController {

    private final SleepService sleepService;

    @GetMapping("/cnt")
    public ResponseEntity<List<SleepCntResponse>> getWakeUpWeekList(@RequestParam Long babyId ,@RequestParam String endDate){
        try {
            List<SleepCntResponse> sleepCntResponseList = sleepService.getWakeUpWeekList(babyId,endDate);
            return new ResponseEntity<>(sleepCntResponseList, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
    }
    @GetMapping("/time")
    public ResponseEntity<List<SleepTimeResponse>> getSleepTimeWeekList(@RequestParam String endDate){
        try {
            List<SleepTimeResponse> sleepTimeResponseList = sleepService.getSleepTimeWeekList(endDate);
            return new ResponseEntity<>(sleepTimeResponseList, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
    }
}
