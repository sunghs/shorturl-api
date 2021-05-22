package sunghs.shorturl.api.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@ApiModel("단축 URL request")
public class ShortUrlRequestDto {

    @ApiModelProperty(value = "단축시킬 원본 URL", required = true)
    private String originalUrl;
}
