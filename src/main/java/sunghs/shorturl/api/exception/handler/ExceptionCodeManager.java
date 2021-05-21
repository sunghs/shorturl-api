package sunghs.shorturl.api.exception.handler;

import lombok.Getter;

public enum ExceptionCodeManager {

    SEQUENCE_OVERFLOW("1001", "최대 요청 가능한 sequence 를 초과하였습니다. 최대값 : %s"),
    SYSTEM_ERROR("9999", "");

    @Getter
    private final String code;

    @Getter
    private final String message;

    ExceptionCodeManager(final String code, final String message) {
        this.code = code;
        this.message = message;
    }
}
