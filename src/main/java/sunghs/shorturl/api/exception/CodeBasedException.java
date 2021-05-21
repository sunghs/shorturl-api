package sunghs.shorturl.api.exception;

import lombok.Getter;
import sunghs.shorturl.api.exception.handler.ExceptionCodeManager;

/**
 * @see ExceptionCodeManager 의 코드를 기반으로 하는 모든 RuntimeException 의 상위 구현체 입니다.
 */
@Getter
public class CodeBasedException extends RuntimeException {

    protected final String errorCode;

    protected final String message;

    public CodeBasedException(ExceptionCodeManager exceptionCodeManager) {
        super(exceptionCodeManager.getMessage());
        this.errorCode = exceptionCodeManager.getCode();
        this.message = exceptionCodeManager.getMessage();
    }

    public CodeBasedException(ExceptionCodeManager exceptionCodeManager, Object... args) {
        super(exceptionCodeManager.getMessage());
        this.errorCode = exceptionCodeManager.getCode();
        this.message = String.format(exceptionCodeManager.getMessage(), args);
    }
}
