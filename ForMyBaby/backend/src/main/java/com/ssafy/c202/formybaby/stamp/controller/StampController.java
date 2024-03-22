package com.ssafy.c202.formybaby.stamp.controller;

import com.ssafy.c202.formybaby.stamp.dto.request.StampCreateRequest;
import com.ssafy.c202.formybaby.stamp.entity.Stamp;
import com.ssafy.c202.formybaby.stamp.service.StampServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/stamp")
@Slf4j
public class StampController {

    private final StampServiceImpl stampService;

    @PostMapping()
    public ResponseEntity<?> createStamp(@RequestBody StampCreateRequest stampCreateRequest) {
        log.info("111111111111");
        stampService.createStamp(stampCreateRequest);
        return new ResponseEntity<>("Stamp updated successfully", HttpStatus.OK);
    }
}
