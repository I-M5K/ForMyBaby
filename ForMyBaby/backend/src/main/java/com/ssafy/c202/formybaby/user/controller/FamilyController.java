package com.ssafy.c202.formybaby.user.controller;

import com.ssafy.c202.formybaby.baby.dto.request.BabyCreateRequest;
import com.ssafy.c202.formybaby.baby.dto.response.BabyReadResponse;
import com.ssafy.c202.formybaby.baby.service.BabyService;
import com.ssafy.c202.formybaby.user.dto.request.FamilyCodeUpdateRequest;
import com.ssafy.c202.formybaby.user.service.FamilyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/v1/users/family")
public class FamilyController {

    private final FamilyService familyService;

    private final BabyService babyService;
    @PatchMapping
    public int joinFamilyWithShareCode(@RequestHeader(name = "Authorization") String token, @RequestBody FamilyCodeUpdateRequest familyCodeUpdateRequest){
        return familyService.checkFamily(token,familyCodeUpdateRequest);
    }

    @PostMapping()
    public ResponseEntity<List<BabyReadResponse>> joinFamilyDirectly(@RequestBody BabyCreateRequest babyCreateRequest) {
        babyService.createNewBaby(babyCreateRequest);
        return new ResponseEntity<>( babyService.babyList(babyCreateRequest.userId()), HttpStatus.CREATED);
    }
}
