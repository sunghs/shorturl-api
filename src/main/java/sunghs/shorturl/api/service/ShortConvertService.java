package sunghs.shorturl.api.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sunghs.shorturl.api.model.ShortUrlComponent;
import sunghs.shorturl.api.model.ShortUrlRequestDto;
import sunghs.shorturl.api.model.ShortUrlResponseDto;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class ShortConvertService {

    private final ShortUrlComponent shortUrlComponent;

    private final UrlConvertService urlConvertService;

    public String getPrefixUrl() {
        return shortUrlComponent.getPrefixUrl();
    }

    public LocalDateTime getValidationTime() {
        return LocalDateTime.now().plusHours(shortUrlComponent.getValidationHours());
    }

    /**
     * 원본 URL을 단축 URL로 변환합니다.
     *
     * @param requestDto ShortUrlRequestDto
     * @return ShortUrlResponseDto
     */
    public ShortUrlResponseDto convert(final ShortUrlRequestDto requestDto) {
        StringBuffer shortUrl = new StringBuffer(getPrefixUrl());

        return new ShortUrlResponseDto();
    }
}
