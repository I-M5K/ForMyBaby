package com.ssafy.c202.formybaby.stamp.dto.request;

public record StampUpdateRequest(String updatedMemo) {

    public StampUpdateRequest(String updatedMemo) {
        this.updatedMemo = updatedMemo;
    }

    public String memo() {
        return this.updatedMemo;
    }
}
