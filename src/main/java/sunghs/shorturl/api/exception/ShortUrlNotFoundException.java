package sunghs.shorturl.api.exception;

import sunghs.shorturl.api.exception.handler.ExceptionCodeManager;

public class ShortUrlNotFoundException extends CodeBasedException {

    public ShortUrlNotFoundException(final ExceptionCodeManager exceptionCodeManager) {
        super(exceptionCodeManager);
    }
}
