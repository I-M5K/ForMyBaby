package com.ssafy.c202.formybaby.baby.controller;

import com.ssafy.c202.formybaby.baby.dto.request.BabyCreateRequest;
import com.ssafy.c202.formybaby.baby.dto.request.BabyUpdateRequest;
import com.ssafy.c202.formybaby.baby.dto.response.BabyReadResponse;
import com.ssafy.c202.formybaby.baby.service.BabyService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/v1/baby")
@CrossOrigin(origins = "http://localhost:3000")
public class BabyController {

    private final BabyService babyService;

    @PostMapping()
    public ResponseEntity<List<BabyReadResponse>> create(@RequestBody BabyCreateRequest babyCreateRequest) {
        babyService.createNewBaby(babyCreateRequest);
        return new ResponseEntity<>( babyService.babyList(babyCreateRequest.userId()), HttpStatus.CREATED);
    }

    @PostMapping("/add")
    public ResponseEntity<List<BabyReadResponse>> add(@RequestBody BabyCreateRequest babyCreateRequest) {
        babyService.createBaby(babyCreateRequest);
        return new ResponseEntity<>( babyService.babyList(babyCreateRequest.userId()), HttpStatus.CREATED);
    }

    @PatchMapping()
    public ResponseEntity<BabyReadResponse> update(@RequestBody BabyUpdateRequest babyUpdateRequest) {
        return new ResponseEntity<>(babyService.updateBaby(babyUpdateRequest), HttpStatus.OK);
    }

    @GetMapping("/{babyId}")
    public ResponseEntity<BabyReadResponse> getDetail(@PathVariable Long babyId) {
        return new ResponseEntity<>(babyService.babyDetail(babyId), HttpStatus.OK);
    }

    @GetMapping("/list/{userId}")
    public ResponseEntity<List<BabyReadResponse>> getList(@PathVariable Long userId) {
        return new ResponseEntity<>(babyService.babyList(userId), HttpStatus.OK);
    }
    @GetMapping("/list/{familyCode}")
    public ResponseEntity<List<BabyReadResponse>> getList(@PathVariable String familyCode) {
        return new ResponseEntity<>(babyService.babyList(familyCode), HttpStatus.OK);
    }

    @DeleteMapping("/{babyId}")
    public ResponseEntity<?> kill(@PathVariable Long babyId) {
        babyService.deleteBaby(babyId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
