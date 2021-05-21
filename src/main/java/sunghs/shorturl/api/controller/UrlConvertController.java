package sunghs.shorturl.api.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "UrlConvertController", tags = "단축 URL 요청 컨트롤러")
@ApiResponses(value = {
    @ApiResponse(code = 200, message = "OK, 성공"),
    @ApiResponse(code = 201, message = "Created, 리소스 생성 성공"),
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
}