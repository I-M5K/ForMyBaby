//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package com.ssafy.c202.formybaby.stopmotion.dto.response;

import java.sql.Timestamp;

public record StopmotionReadResponse(Long motionId, Long babyId, String motionUrl, Timestamp createdAt) {
    public StopmotionReadResponse(Long motionId, Long babyId, String motionUrl, Timestamp createdAt) {
        this.motionId = motionId;
        this.babyId = babyId;
        this.motionUrl = motionUrl;
        this.createdAt = createdAt;
    }

    public Long motionId() {
        return this.motionId;
    }

    public Long babyId() {
        return this.babyId;
    }

    public String motionUrl() {
        return this.motionUrl;
    }

    public Timestamp createdAt() {
        return this.createdAt;
    }
}
