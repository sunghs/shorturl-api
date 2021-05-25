package sunghs.shorturl.api.exception.handler;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import sunghs.shorturl.api.exception.AlreadyExistException;
import sunghs.shorturl.api.exception.CharacterNotFoundException;
import sunghs.shorturl.api.exception.CodeBasedException;
import sunghs.shorturl.api.exception.InvalidUrlException;
import sunghs.shorturl.api.exception.PageNotFoundException;
import sunghs.shorturl.api.exception.SequenceOverFlowException;
import sunghs.shorturl.api.exception.ShortUrlNotFoundException;
import sunghs.shorturl.api.model.ErrorResponseDto;
import sunghs.shorturl.api.model.ShortUrlComponent;

@Slf4j
@RestControllerAdvice
@RequiredArgsConstructor
public class ExceptionControllerAdvice {

    private final ShortUrlComponent shortUrlComponent;

    /**
     * 예기치 못한 전체 에러에 대해 아래 advice 가 수행됩니다.
     *
     * @param httpServletRequest httpServletRequest
     * @param throwable          throwable
     * @return ErrorResponseDto
     */
    @ExceptionHandler(Throwable.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponseDto throwableAdvice(final HttpServletRequest httpServletRequest, final Throwable throwable) {
        log.error("method : {}, uri : {}", httpServletRequest.getMethod(), httpServletRequest.getRequestURI(), throwable);

        return ErrorResponseDto.builder()
            .errorCode(ExceptionCodeManager.SYSTEM_ERROR.getCode())
            .message(throwable.getMessage())
            .build();
    }

    /**
     * 기본적인 ExceptionCodeManager 로 핸들링 되는 예외에 대하여 수행합니다.
     *
     * @param httpServletRequest httpServletRequest
     * @param exception          codeBasedException
     * @return ErrorResponseDto
     */
    @ExceptionHandler(CodeBasedException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponseDto defaultCheckedAdvice(final HttpServletRequest httpServletRequest, final CodeBasedException exception) {
        errorLog(httpServletRequest.getMethod(), httpServletRequest.getRequestURI(), exception.getMessage());
        return extractResponse(exception);
    }

    /**
     * 검증상의 문제인 경우 200 과 메시지를 내려줍니다.
     *
     * @param httpServletRequest httpServletRequest
     * @param exception CodeBasedException
     * @return ErrorResponseDto
     */
    @ExceptionHandler({
        ShortUrlNotFoundException.class,
        AlreadyExistException.class,
        InvalidUrlException.class,
    })
    @ResponseStatus(HttpStatus.OK)
    public ErrorResponseDto expectedAdvice(final HttpServletRequest httpServletRequest, final CodeBasedException exception) {
        errorLog(httpServletRequest.getMethod(), httpServletRequest.getRequestURI(), exception.getMessage());
        return extractResponse(exception);
    }

    /**
     * 검증 문제가 아닌 시스템 상의 문제인 경우 500 에러를 내려줍니다.
     *
     * @param httpServletRequest httpServletRequest
     * @param exception CodeBasedException
     * @return ErrorResponseDto
     */
    @ExceptionHandler({
        CharacterNotFoundException.class,
        SequenceOverFlowException.class
    })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponseDto systemExpectedAdvice(final HttpServletRequest httpServletRequest, final CodeBasedException exception) {
        errorLog(httpServletRequest.getMethod(), httpServletRequest.getRequestURI(), exception.getMessage());
        return extractResponse(exception);
    }

    /**
     * redirect 에러가 발생한 경우 default page 로 넘깁니다.
     *
     * @param httpServletResponse httpServletResponse
     * @throws IOException sendRedirect error
     */
    @ExceptionHandler(PageNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void redirectAdvice(final HttpServletResponse httpServletResponse) throws IOException {
        httpServletResponse.sendRedirect(shortUrlComponent.getDefaultRedirectUrl());
    }

    private void errorLog(String method, String uri, String message) {
        log.error("method : {}, uri : {}, message : {}", method, uri, message);
    }

    private ErrorResponseDto extractResponse(CodeBasedException exception) {
        return ErrorResponseDto.builder()
            .errorCode(exception.getErrorCode())
            .message(exception.getMessage())
            .build();
    }
}
