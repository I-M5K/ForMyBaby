package com.ssafy.c202.formybaby.stopmotion.controller;

import com.ssafy.c202.formybaby.global.s3.AwsS3Service;
import com.ssafy.c202.formybaby.stopmotion.dto.response.StopmotionCntResponse;
import com.ssafy.c202.formybaby.stopmotion.dto.response.StopmotionVideoResponse;
import com.ssafy.c202.formybaby.stopmotion.service.StopmotionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/v1/stopmotion")
@RequiredArgsConstructor
public class StopmotionController {

    private final StopmotionService stopmotionService;
    private final AwsS3Service awsS3Service;

    @GetMapping("/{babyId}")
    public ResponseEntity<StopmotionVideoResponse> getStopmotionVideo(@PathVariable Long babyId) {
        try {
            String motionUrl = stopmotionService.findMotionUrlByBabyId(babyId);
            StopmotionVideoResponse response = new StopmotionVideoResponse(motionUrl);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/count/{babyId}")
    public ResponseEntity<StopmotionCntResponse> getCountByBabyId(@PathVariable Long babyId) {
        try {
            int imageCount = stopmotionService.countImagesByBabyId(babyId);
            StopmotionCntResponse response = new StopmotionCntResponse(imageCount);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/videoUrl/{fileName}")
    public ResponseEntity<?> getVideoUrl(@PathVariable String fileName) {
        try {
            String videoUrl = awsS3Service.getVideoUrl(fileName);
            return ResponseEntity.ok(videoUrl);
        } catch (ResponseStatusException e) {
            return ResponseEntity.status(e.getStatusCode()).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("서버 내부 오류가 발생했습니다.");
        }
    }



}
