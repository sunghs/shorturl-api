package sunghs.shorturl.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestConstructor;
import org.springframework.transaction.annotation.Transactional;
import sunghs.shorturl.api.model.ShortUrlRequestDto;
import sunghs.shorturl.api.model.ShortUrlResponseDto;
import sunghs.shorturl.api.service.ShortConvertService;

import java.time.LocalDateTime;

@Slf4j
@SpringBootTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@RequiredArgsConstructor
class ShortConvertServiceTests {

    private final ShortConvertService shortConvertService;

    private ShortUrlRequestDto shortUrlRequestDto = new ShortUrlRequestDto();

    @BeforeEach
    void beforeEach() {
        shortUrlRequestDto.setOriginalUrl("http://www.example.com");
    }

    @Test
    @Transactional
    @DisplayName("단축 Url 변환 테스트")
    void shortConvertTest() {
        log.info(">>>>>>>>>>>>>>>>>>>>>> {}", shortUrlRequestDto);
        ShortUrlResponseDto shortUrlResponseDto = shortConvertService.convert(shortUrlRequestDto);
        log.info("result : {}", shortUrlResponseDto.toString());

        Assertions.assertTrue(shortUrlResponseDto.getExpireDt().isAfter(LocalDateTime.now()));
        Assertions.assertTrue(StringUtils.isNotEmpty(shortUrlResponseDto.getShortUrl()));
    }
}
