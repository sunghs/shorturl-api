package sunghs.shorturl.api.service;

import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestConstructor;
import org.springframework.transaction.annotation.Transactional;
import sunghs.shorturl.api.exception.InvalidUrlException;
import sunghs.shorturl.api.model.ShortUrlRequestDto;
import sunghs.shorturl.api.model.ShortUrlResponseDto;

@Slf4j
@SpringBootTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@RequiredArgsConstructor
class ShortConvertServiceTests {

    private final ShortConvertService shortConvertService;

    @Test
    @Transactional
    @DisplayName("단축 Url 변환 테스트")
    void shortConvertTest() {
        // given
        ShortUrlRequestDto shortUrlRequestDto = new ShortUrlRequestDto();
        shortUrlRequestDto.setOriginalUrl("http://www.test.com");

        // when
        ShortUrlResponseDto shortUrlResponseDto = shortConvertService.convert(shortUrlRequestDto);

        // then
        Assertions.assertTrue(shortUrlResponseDto.getExpireDt().isAfter(LocalDateTime.now()));
        Assertions.assertTrue(StringUtils.isNotEmpty(shortUrlResponseDto.getShortUrl()));
    }

    @Test
    @Transactional
    @DisplayName("유효하지 않은 단축 Url 변환 테스트")
    void shortConvertErrorParameterTest() {
        // given
        ShortUrlRequestDto shortUrlRequestDto = new ShortUrlRequestDto();
        shortUrlRequestDto.setOriginalUrl("www.test.com");

        // when & then
        Assertions.assertThrows(InvalidUrlException.class, () -> shortConvertService.convert(shortUrlRequestDto));
    }
}
