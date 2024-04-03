package com.ssafy.c202.formybaby.stamp.controller;

import com.ssafy.c202.formybaby.baby.repository.BabyRepository;
import com.ssafy.c202.formybaby.stamp.dto.request.StampCreateAIRequest;
import com.ssafy.c202.formybaby.stamp.dto.request.StampCreateRequest;
import com.ssafy.c202.formybaby.stamp.dto.request.StampUpdateAIRequest;
import com.ssafy.c202.formybaby.stamp.dto.request.StampUpdateRequest;
import com.ssafy.c202.formybaby.stamp.dto.response.StampListResponse;
import com.ssafy.c202.formybaby.stamp.service.StampService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/stamp")
@Slf4j
public class StampController {

    private final StampService stampService;

    @PostMapping()
    public ResponseEntity<?> createStamp(@RequestHeader(name = "Authorization") String token, @RequestParam(value = "memo")String memo, @RequestParam(value = "step")Long step, @RequestParam(value = "stampImg")MultipartFile stampImg) {
        StampCreateRequest stampCreateRequest = new StampCreateRequest(step, stampImg, memo);
        stampService.createStamp(token, stampCreateRequest);
        return new ResponseEntity<>("Stamp create successfully^^!", HttpStatus.OK);
    }

    @PatchMapping("/create")
    public ResponseEntity<?> createStampAI(@RequestHeader(name = "Authorization") String token, @RequestBody StampCreateAIRequest stampCreateAIRequest){
        System.out.println(stampCreateAIRequest.stampUrl() + " " + stampCreateAIRequest.babyId() + " " + stampCreateAIRequest.step());
        stampService.createStampAI(token, stampCreateAIRequest);
        return null;
    }

    @GetMapping("/list")
    public ResponseEntity<List<StampListResponse>> listStamp(@RequestHeader(name = "Authorization") String token) {
        List<StampListResponse> stampListResponseList = stampService.stampListResponse(token);
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
    @PatchMapping()
    public ResponseEntity<?> updateAIStamp(@RequestParam Long stampId , @RequestBody StampUpdateAIRequest stampUpdateAIRequest) {
        stampService.updateAIStamp(stampId,stampUpdateAIRequest);
        return new ResponseEntity<>("Stamp update successfully>0<!", HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity<?> deleteStamp(@RequestParam Long stampId){
        stampService.deleteStamp(stampId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
