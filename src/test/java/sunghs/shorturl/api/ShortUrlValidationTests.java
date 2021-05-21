package sunghs.shorturl.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestConstructor;
import sunghs.shorturl.api.model.ShortUrlComponent;

@Slf4j
@SpringBootTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@RequiredArgsConstructor
public class ShortUrlValidationTests {

    private final ShortUrlComponent shortUrlComponent;

    @Test
    @DisplayName("application.yml 의 short 설정 확인 테스트")
    void componentValidationTest() {
        log.info(shortUrlComponent.toString());
        Assertions.assertNotNull(shortUrlComponent);
    }

    @Test
    @DisplayName("설정 임계 sequence 테스트")
    void limitedCountTest() {
        long limitedCount = (long) Math.pow(shortUrlComponent.getCharacterList().length(), shortUrlComponent.getLimitedCharacterSize());
        log.info("limitedCount : {}", limitedCount);
        Assertions.assertTrue(limitedCount > 0);
    }
}
