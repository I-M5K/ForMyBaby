package com.ssafy.c202.formybaby.user.controller;

import com.ssafy.c202.formybaby.baby.dto.request.BabyCreateRequest;
import com.ssafy.c202.formybaby.baby.dto.response.BabyReadResponse;
import com.ssafy.c202.formybaby.baby.dto.response.BabyReadResponse2;
import com.ssafy.c202.formybaby.baby.service.BabyService;
import com.ssafy.c202.formybaby.user.dto.request.FamilyCodeCreateRequest;
import com.ssafy.c202.formybaby.user.dto.request.FamilyCodeUpdateRequest;
import com.ssafy.c202.formybaby.user.dto.response.FamilyReadResponse;
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
@RequestMapping("/v1/users")
public class FamilyController {

    private final FamilyService familyService;

    private final BabyService babyService;
    @GetMapping("/family")
    public ResponseEntity<List<BabyReadResponse2>> checkFamily(@RequestHeader(name = "Authorization") String token,
                                                               @RequestBody FamilyCodeUpdateRequest familyCodeUpdateRequest){
        try{
            return new ResponseEntity<List<BabyReadResponse2>>(familyService.checkFamily(token,familyCodeUpdateRequest),HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/role")
    public ResponseEntity<?> joinFamilyWithShareCode(@RequestHeader(name = "Authorization") String token, @RequestBody FamilyCodeCreateRequest familyCodeCreateRequest){
        List<BabyReadResponse2> babyReadResponse2List = familyService.joinFamilyWithShareCode(token, familyCodeCreateRequest);
        try{
            return new ResponseEntity<>(babyReadResponse2List,HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
        }
    }
    @PostMapping("/family")
    public ResponseEntity<FamilyReadResponse> joinFamilyDirectly(@RequestHeader(name = "Authorization") String token, @RequestBody BabyCreateRequest babyCreateRequest) {
        try{
            return new ResponseEntity<>(babyService.createNewBaby2(token, babyCreateRequest), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
