package sunghs.shorturl.api.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ShortUrlRequestDto {

    private String originalUrl;
}
