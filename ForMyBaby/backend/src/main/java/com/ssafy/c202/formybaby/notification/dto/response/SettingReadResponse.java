package com.ssafy.c202.formybaby.notification.dto.response;

public record SettingReadResponse(
        Boolean isGeneral,
        Boolean isDanger,
        Boolean isSound
) {
}
