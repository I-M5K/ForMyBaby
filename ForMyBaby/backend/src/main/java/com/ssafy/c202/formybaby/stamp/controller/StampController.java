package com.ssafy.c202.formybaby.stamp.controller;

import com.ssafy.c202.formybaby.baby.repository.BabyRepository;
import com.ssafy.c202.formybaby.stamp.dto.request.StampCreateRequest;
import com.ssafy.c202.formybaby.stamp.dto.request.StampUpdateRequest;
import com.ssafy.c202.formybaby.stamp.dto.response.StampListResponse;
import com.ssafy.c202.formybaby.stamp.service.StampService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping("/list")
    public ResponseEntity<List<StampListResponse>> listStamp(@RequestParam Long babyId) {
        List<StampListResponse> stampListResponseList = stampService.stampListResponse(babyId);
        if (stampListResponseList != null && !stampListResponseList.isEmpty()) {
            return ResponseEntity.ok(stampListResponseList);
        } else {
            return ResponseEntity.notFound().build(); // Stamp 데이터가 없는 경우 404 에러 반환
        }
    }

    @GetMapping("/detail")
    public ResponseEntity<StampListResponse> detailStamp(@RequestParam Long stampId) {
        try{
            StampListResponse stampListResponse = stampService.detailStamp(stampId);
            return new ResponseEntity<>(stampListResponse, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }


    @PutMapping()
    public ResponseEntity<?> updateStamp(@RequestParam Long stampId , @RequestBody StampUpdateRequest stampUpdateRequest) {
        stampService.updateStamp(stampId,stampUpdateRequest);
        return new ResponseEntity<>("Stamp update successfully>0<!", HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity<?> deleteStamp(@RequestParam Long stampId){
        stampService.deleteStamp(stampId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
