package sunghs.shorturl.api.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("단축 URL response")
public class ShortUrlResponseDto {

    @ApiModelProperty("원본 URL")
    private String originalUrl;

    @ApiModelProperty("변환 된 단축 URL")
    private String shortUrl;

    @ApiModelProperty("요청 횟수")
    private int requestCount;

    @ApiModelProperty("생성 시간")
    private LocalDateTime createDt;

    @ApiModelProperty("만료 시간")
    private LocalDateTime expireDt;
}
