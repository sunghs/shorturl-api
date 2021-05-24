package sunghs.shorturl.api.exception;

import sunghs.shorturl.api.exception.handler.ExceptionCodeManager;

public class InvalidUrlException extends CodeBasedException {

    public InvalidUrlException(final ExceptionCodeManager exceptionCodeManager) {
        super(exceptionCodeManager);
    }
}
