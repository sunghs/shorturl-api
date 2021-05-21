package sunghs.shorturl.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class ShortUrlResponseDto {

    private String originalUrl;

    private String shortUrl;

    private int requestCount;

    private LocalDateTime createDt;
}
