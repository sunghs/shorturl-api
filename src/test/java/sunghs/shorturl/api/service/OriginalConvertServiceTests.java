package sunghs.shorturl.api.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestConstructor;
import org.springframework.transaction.annotation.Transactional;
import sunghs.shorturl.api.model.OriginalUrlRequestDto;
import sunghs.shorturl.api.model.OriginalUrlResponseDto;
import sunghs.shorturl.api.model.ShortUrlRequestDto;
import sunghs.shorturl.api.model.ShortUrlResponseDto;

@Slf4j
@SpringBootTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@RequiredArgsConstructor
@Transactional
class OriginalConvertServiceTests {

    private final ShortConvertService shortConvertService;

    private final OriginalConvertService originalConvertService;

    @Test
    @DisplayName("원본 Url 변환 테스트")
    void originalConvertTest() {
        // original url to short url
        // given
        ShortUrlRequestDto shortUrlRequestDto = new ShortUrlRequestDto();
        shortUrlRequestDto.setOriginalUrl("https://www.test.com");

        // when
        ShortUrlResponseDto shortUrlResponseDto = shortConvertService.convert(shortUrlRequestDto);

        // then
        Assertions.assertEquals(1, shortUrlResponseDto.getRequestCount());
        Assertions.assertTrue(StringUtils.isNotEmpty(shortUrlResponseDto.getShortUrl()));

        // short url to original url
        // given
        OriginalUrlRequestDto originalUrlRequestDto = new OriginalUrlRequestDto();
        originalUrlRequestDto.setShortUrl(shortUrlResponseDto.getShortUrl());

        // when
        OriginalUrlResponseDto originalUrlResponseDto = originalConvertService.convert(originalUrlRequestDto);

        // then
        Assertions.assertEquals(originalUrlResponseDto.getOriginalUrl(), shortUrlRequestDto.getOriginalUrl());
        Assertions.assertEquals(2, originalUrlResponseDto.getRequestCount());
    }
}
