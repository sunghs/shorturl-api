package sunghs.shorturl.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestConstructor;
import sunghs.shorturl.api.service.UrlConvertService;

@Slf4j
@SpringBootTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@RequiredArgsConstructor
public class UrlConvertTests {

    private final UrlConvertService urlConvertService;

    @ParameterizedTest
    @ValueSource(longs = {1, 293, 3531, 59128, 927852, 8276302, 348728301, 289379234, 689572983, Long.MAX_VALUE})
    @DisplayName("sequence 기반 url convert 테스트")
    void shortUrlConvertTest(long given) {
        String actual = urlConvertService.convertSeqToUrl(given);
        Assertions.assertTrue(actual.length() > 0);
    }

    @ParameterizedTest
    @ValueSource(longs = {218_340_105_584_895L, 218_340_105_584_896L, 218_340_105_584_897L})
    @DisplayName("변환 시 8자리를 넘기는 임계값 테스트")
    void limitedSizeTest(long given) {
        String actual = urlConvertService.convertSeqToUrl(given);

        if (given == 218_340_105_584_895L) {
            Assertions.assertEquals(8, actual.length());
        } else {
            Assertions.assertEquals(9, actual.length());
        }
    }
}
