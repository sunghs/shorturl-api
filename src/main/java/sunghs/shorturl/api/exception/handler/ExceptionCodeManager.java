package sunghs.shorturl.api.exception.handler;

import lombok.Getter;

public enum ExceptionCodeManager {

    SEQUENCE_OVERFLOW("1001", "최대 요청 가능한 sequence 를 초과하였습니다. 최대값 : %s"),
    CHARACTER_NOT_FOUND("1002", "문자열을 찾지 못했습니다. 사유 : %s"),
    ALREADY_SHORT_URL_EXIST("1003", "이미 단축 URL이 존재합니다."),
    SHORT_URL_NOT_FOUND("1004", "유효하지 않은 단축 URL 입니다."),
    INVALID_URL("1005", "유효하지 않은 URL 입니다."),
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
