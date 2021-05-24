package sunghs.shorturl.api.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;
import sunghs.shorturl.api.model.entity.ShortUrlInfo;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("원본 URL response")
public class OriginalUrlResponseDto {

    @ApiModelProperty("원본 URL")
    private String originalUrl;

    @ApiModelProperty("요청 횟수")
    private long requestCount;

    public static OriginalUrlResponseDto of(final ShortUrlInfo shortUrlInfo) {
        return new OriginalUrlResponseDto(shortUrlInfo.getOriginalUrl(), shortUrlInfo.getRequestCount());
    }
}
