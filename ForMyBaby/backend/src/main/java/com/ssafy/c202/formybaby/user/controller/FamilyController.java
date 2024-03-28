package com.ssafy.c202.formybaby.user.controller;

import com.ssafy.c202.formybaby.baby.dto.request.BabyCreateRequest;
import com.ssafy.c202.formybaby.baby.dto.response.BabyReadResponse;
import com.ssafy.c202.formybaby.baby.dto.response.BabyReadResponse2;
import com.ssafy.c202.formybaby.baby.service.BabyService;
import com.ssafy.c202.formybaby.global.jpaEnum.BabyGender;
import com.ssafy.c202.formybaby.global.jpaEnum.Role;
import com.ssafy.c202.formybaby.user.dto.request.FamilyCodeCreateRequest;
import com.ssafy.c202.formybaby.user.dto.request.FamilyCodeUpdateRequest;
import com.ssafy.c202.formybaby.user.dto.response.FamilyReadResponse;
import com.ssafy.c202.formybaby.user.service.FamilyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;
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
                                                               @RequestParam(name = "familyCode") String familyCode){
        FamilyCodeUpdateRequest familyCodeUpdateRequest = new FamilyCodeUpdateRequest(familyCode, Role.None);

 //       try{
            return new ResponseEntity<List<BabyReadResponse2>>(familyService.checkFamily(token,familyCodeUpdateRequest),HttpStatus.OK);
//        }catch (Exception e){
//            return new ResponseEntity<>(null,HttpStatus.BAD_REQUEST);
//        }
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
    public ResponseEntity<FamilyReadResponse> joinFamilyDirectly(
            @RequestHeader(name = "Authorization") String token,
            @RequestParam("userId") Long userId,
            @RequestParam("babyName") String babyName,
            @RequestParam("babyGender") BabyGender babyGender,
            @RequestParam("babyBirthDate") String babyBirthDate,
            @RequestParam("profileImg") MultipartFile profileImg,
            @RequestParam("role") Role role
    ) {
        try {
            BabyCreateRequest babyCreateRequest = new BabyCreateRequest(
                    userId,
                    babyName,
                    babyBirthDate,
                    babyGender,
                    profileImg,
                    role
            );
            return new ResponseEntity<>(babyService.createNewBabyNoShareCode(token, babyCreateRequest), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

}
