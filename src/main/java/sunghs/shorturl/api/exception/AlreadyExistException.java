package sunghs.shorturl.api.exception;

import sunghs.shorturl.api.exception.handler.ExceptionCodeManager;

public class AlreadyExistException extends CodeBasedException {

    public AlreadyExistException(final ExceptionCodeManager exceptionCodeManager) {
        super(exceptionCodeManager);
    }
}
