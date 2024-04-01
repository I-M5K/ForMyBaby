package com.ssafy.c202.formybaby.sleep.controller;

import com.ssafy.c202.formybaby.global.jpaEnum.DangerType;
import com.ssafy.c202.formybaby.sleep.dto.response.DangerCntResponse;
import com.ssafy.c202.formybaby.sleep.dto.request.DangerCreateRequest;
import com.ssafy.c202.formybaby.sleep.dto.response.DangerReadResponse;
import com.ssafy.c202.formybaby.sleep.service.DangerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/v1/danger")
public class DangerController {

    private final DangerService dangerService;

    @GetMapping("/")
    public ResponseEntity<List<DangerReadResponse>> selectWeekDangerList(@RequestParam Long babyId) {
        try {
            List<DangerReadResponse> dangerReadResponseList = dangerService.selectWeekDangerList(babyId);
            return new ResponseEntity<>(dangerReadResponseList, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/cnt")
    public ResponseEntity<List<DangerCntResponse>> selectWeekDangerCntList(@RequestParam int dangerCnt){
        try {
            List<DangerCntResponse> dangerCntResponseList = dangerService.selectWeekDangerCntList(dangerCnt);
            return new ResponseEntity<>(dangerCntResponseList, HttpStatus.OK);
        } catch (Exception e) {
            return  new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
    @GetMapping
    public ResponseEntity<?> createDanger(@RequestHeader("Authorization") String code, @RequestParam DangerType dangerType, @RequestParam Long babyId) {
        log.info("createDanger");
        dangerService.createDanger(code, dangerType, babyId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
