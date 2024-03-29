package sunghs.shorturl.api.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import sunghs.shorturl.api.model.entity.ShortUrlInfo;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("단축 URL response")
public class ShortUrlResponseDto {

    @ApiModelProperty("변환 된 단축 URL")
    private String shortUrl;

    @ApiModelProperty("요청 횟수")
    private long requestCount;

    @ApiModelProperty("만료 시간")
    private LocalDateTime expireDt;

    public static ShortUrlResponseDto of(final ShortUrlInfo shortUrlInfo) {
        return new ShortUrlResponseDto(shortUrlInfo.getShortUrl(), shortUrlInfo.getRequestCount(), shortUrlInfo.getExpireDt());
    }
}
