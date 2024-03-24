package com.ssafy.c202.formybaby.stamp.controller;

import com.ssafy.c202.formybaby.stamp.dto.request.StampCreateRequest;
import com.ssafy.c202.formybaby.stamp.service.StampService;
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

    private final StampService stampService;
    @PostMapping()
    public ResponseEntity<?> createStamp(@RequestBody StampCreateRequest stampCreateRequest) {
        stampService.createStamp(stampCreateRequest);
        //ResponseEntity responseEntity = new ResponseEntity("Stamp update successfully", HttpStatus.OK);
        return new ResponseEntity<>("Stamp create successfully^^!", HttpStatus.OK);
    }
}
