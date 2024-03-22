package com.ssafy.c202.formybaby.stamp.dto.request;

public record StampUpdateRequest(String memo) {
    public StampUpdateRequest(String memo) {
        this.memo = memo;
    }

    public String memo() {
        return this.memo;
    }
}
