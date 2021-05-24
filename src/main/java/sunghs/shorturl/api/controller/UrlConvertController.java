package sunghs.shorturl.api.controller;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sunghs.shorturl.api.model.OriginalUrlRequestDto;
import sunghs.shorturl.api.model.OriginalUrlResponseDto;
import sunghs.shorturl.api.model.ShortUrlRequestDto;
import sunghs.shorturl.api.model.ShortUrlResponseDto;
import sunghs.shorturl.api.service.OriginalConvertService;
import sunghs.shorturl.api.service.ShortConvertService;

import javax.validation.Valid;

@Api(value = "UrlConvertController", tags = "단축 URL 요청 컨트롤러")
@ApiResponses(value = {
    @ApiResponse(code = 200, message = "OK, 성공"),
    @ApiResponse(code = 307, message = "Temporary Redirect, URI가 변경 됨"),
    @ApiResponse(code = 401, message = "Unauthorized, 인증되지 않음"),
    @ApiResponse(code = 403, message = "Forbidden, 권한이 없음"),
    @ApiResponse(code = 404, message = "없는 URL 또는 요청 자원을 찾을 수 없음"),
    @ApiResponse(code = 500, message = "Internal Server Error, 서버 에러"),
    @ApiResponse(code = 503, message = "Service Unavailable, 서비스 요청 불가"),
    @ApiResponse(code = 504, message = "Gateway Timeout, 응답시간 초과")
})
@RestController
@RequestMapping(value = "/url", produces = {MediaType.APPLICATION_JSON_VALUE})
@RequiredArgsConstructor
public class UrlConvertController {

    private final ShortConvertService shortConvertService;

    private final OriginalConvertService originalConvertService;

    @ApiOperation("단축 Url 정보 요청")
    @PostMapping("/short")
    public ShortUrlResponseDto getShortUrl(@ApiParam(value = "원본 Url", required = true) @Valid @RequestBody final ShortUrlRequestDto requestDto) {
        return shortConvertService.convert(requestDto);
    }

    @ApiOperation("원본 Url 정보 요청")
    @PostMapping("/original")
    public OriginalUrlResponseDto getOriginalUrl(@ApiParam(value = "단축 Url", required = true) @Valid @RequestBody final OriginalUrlRequestDto requestDto) {
        return originalConvertService.convert(requestDto);
    }
}
