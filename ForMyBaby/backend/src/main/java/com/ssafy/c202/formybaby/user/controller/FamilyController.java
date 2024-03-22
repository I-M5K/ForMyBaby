package com.ssafy.c202.formybaby.user.controller;

import com.ssafy.c202.formybaby.user.dto.request.FamilyCodeUpdateRequest;
import com.ssafy.c202.formybaby.user.service.FamilyService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/v1/users/family")
public class FamilyController {

    private final FamilyService familyService;

    @GetMapping
    public ResponseEntity<?> checkFamilyCode(@RequestBody FamilyCodeUpdateRequest familyCodeUpdateRequest){
        familyService.checkFamilyCode(familyCodeUpdateRequest);
        return null;
    }
}
