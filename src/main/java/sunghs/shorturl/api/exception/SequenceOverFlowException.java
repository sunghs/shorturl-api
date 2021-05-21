package sunghs.shorturl.api.exception;

import sunghs.shorturl.api.exception.handler.ExceptionCodeManager;

public class SequenceOverFlowException extends CodeBasedException {

    public SequenceOverFlowException(final ExceptionCodeManager exceptionCodeManager, long value) {
        super(exceptionCodeManager, String.valueOf(value));
    }
}
