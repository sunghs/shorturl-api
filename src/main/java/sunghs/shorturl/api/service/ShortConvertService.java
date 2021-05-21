package sunghs.shorturl.api.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sunghs.shorturl.api.model.ShortUrlRequestDto;
import sunghs.shorturl.api.model.ShortUrlResponseDto;

@Slf4j
@Service
@RequiredArgsConstructor
public class ShortConvertService {

    private final UrlConvertService urlConvertService;

    public ShortUrlResponseDto convert(final ShortUrlRequestDto requestDto) {
        StringBuffer shortUrl = new StringBuffer(urlConvertService.getPrefixUrl());

        return new ShortUrlResponseDto();
    }
}
