package com.ssafy.c202.formybaby.stopmotion.controller;

import com.ssafy.c202.formybaby.global.s3.AwsS3Service;
import com.ssafy.c202.formybaby.stopmotion.dto.response.StopMotionCntResponse;
import com.ssafy.c202.formybaby.stopmotion.dto.response.StopMotionVideoResponse;
import com.ssafy.c202.formybaby.stopmotion.entity.StopMotion;
import com.ssafy.c202.formybaby.stopmotion.service.StopMotionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("/v1/stopmotion")
@RequiredArgsConstructor
public class StopMotionController {

    private final StopMotionService stopMotionService;
    private final AwsS3Service awsS3Service;

    @GetMapping
    public ResponseEntity<StopMotionVideoResponse> getStopmotionVideo(@RequestHeader(name = "Authorization") String token) {
        try {
            String motionUrl = stopMotionService.findMotionUrlByBabyId(token);
            StopMotionVideoResponse response = new StopMotionVideoResponse(motionUrl);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/create")
    public ResponseEntity<?> createStopMotionImage(@RequestHeader(name = "Authorization") String token, @RequestParam String imageUrl){
        try {
            stopMotionService.createStopMotionImage(token,imageUrl);
            return ResponseEntity.ok().build(); // 성공했을 때 200 OK 반환
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred: " + e.getMessage()); // 에러가 발생했을 때 500 Internal Server Error 반환
        }
    }

    @GetMapping("/count")
    public ResponseEntity<StopMotionCntResponse> getCountByBabyId(@RequestHeader(name = "Authorization") String token) {
        try {
            int imageCount = stopMotionService.countImagesByBabyId(token);
            StopMotionCntResponse response = new StopMotionCntResponse(imageCount);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/videoUrl/{fileName}")
    public ResponseEntity<?> getVideoUrl(@PathVariable String fileName) {
        try {
            String videoUrl = awsS3Service.getUrl(fileName);
            return ResponseEntity.ok(videoUrl);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("서버 내부 오류가 발생했습니다.");
        }
    }



}