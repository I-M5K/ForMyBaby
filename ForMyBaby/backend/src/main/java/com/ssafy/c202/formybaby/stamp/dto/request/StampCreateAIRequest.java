package com.ssafy.c202.formybaby.stamp.dto.request;

import org.springframework.web.multipart.MultipartFile;

import java.sql.Timestamp;

public record StampCreateAIRequest(
        Long step,
        String stampUrl,
        String memo,

        Long babyId
){}

