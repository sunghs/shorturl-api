package sunghs.shorturl.api.exception;

import sunghs.shorturl.api.exception.handler.ExceptionCodeManager;

public class CharacterNotFoundException extends CodeBasedException {

    public CharacterNotFoundException(final ExceptionCodeManager exceptionCodeManager, String message) {
        super(exceptionCodeManager, message);
    }
}
