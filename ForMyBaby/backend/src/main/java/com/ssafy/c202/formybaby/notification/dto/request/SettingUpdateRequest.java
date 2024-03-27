package com.ssafy.c202.formybaby.notification.dto.request;

public record SettingUpdateRequest(
        Boolean isGeneral,
        Boolean isDanger,
        Boolean isSound
) {
}
