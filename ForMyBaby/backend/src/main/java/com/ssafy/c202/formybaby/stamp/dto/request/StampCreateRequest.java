package com.ssafy.c202.formybaby.stamp.dto.request;

public record StampCreateRequest(Long step, String memo, String createdAt) {
    public StampCreateRequest(Long step, String memo, String createdAt) {
        this.step = step;
        this.memo = memo;
        this.createdAt = createdAt;
    }

    public Long step() {
        return this.step;
    }

    public String memo() {
        return this.memo;
    }

    public String createdAt() {
        return this.createdAt;
    }
}
