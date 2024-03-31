package com.ssafy.c202.formybaby.stamp.dto.request;

import org.springframework.web.multipart.MultipartFile;

public record StampUpdateAIRequest(
        String stampUrl,
        String memo
) {}
