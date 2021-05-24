package sunghs.shorturl.api.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@ApiModel("원본 URL request")
public class OriginalUrlRequestDto {

    @ApiModelProperty(value = "원본을 찾을 단축 URL", required = true)
    private String shortUrl;
}
