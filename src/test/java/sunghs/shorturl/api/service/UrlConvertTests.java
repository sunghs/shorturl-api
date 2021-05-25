package sunghs.shorturl.api.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestConstructor;
import sunghs.shorturl.api.exception.SequenceOverFlowException;

@Slf4j
@SpringBootTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@RequiredArgsConstructor
class UrlConvertTests {

    private final UrlConvertService urlConvertService;

    @ParameterizedTest
    @ValueSource(longs = {1, 293, 3531, 59128, 927852, 8276302, 348728301, 289379234, 689572983, 218340105584895L})
    @DisplayName("sequence 기반 url convert 테스트")
    void shortUrlConvertTest(long given) {
        // when
        String actual = urlConvertService.convertSeqToUrl(given);

        // then
        Assertions.assertTrue(actual.length() > 0 && actual.length() <= 8);
    }

    @ParameterizedTest
    @ValueSource(longs = {1, 293, 3531, 59128, 927852, 8276302, 348728301, 289379234, 689572983, 218340105584895L})
    @DisplayName("sequence 기반 url convert 후 다시 sequence 변환 테스트")
    void originalSeqConvertTest(long given) {
        // when
        String actual = urlConvertService.convertSeqToUrl(given);
        long convertedSeq =  urlConvertService.convertUrlToSeq(actual);

        // then
        Assertions.assertEquals(given, convertedSeq);
    }

    @ParameterizedTest
    @ValueSource(longs = {218340105584896L, 218340105584897L, Long.MAX_VALUE})
    @DisplayName("최대값이 넘는 경우 테스트")
    void overFlowSeqTest(long given) {
        // when & then
        Assertions.assertThrows(SequenceOverFlowException.class, () -> urlConvertService.convertSeqToUrl(given));
    }
}
