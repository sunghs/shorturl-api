package sunghs.shorturl.api.exception;

import sunghs.shorturl.api.exception.handler.ExceptionCodeManager;

public class PageNotFoundException extends CodeBasedException {

    public PageNotFoundException(final ExceptionCodeManager exceptionCodeManager) {
        super(exceptionCodeManager);
    }
}
