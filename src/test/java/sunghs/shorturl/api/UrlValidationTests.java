package sunghs.shorturl.api;

import org.apache.commons.validator.routines.RegexValidator;
import org.apache.commons.validator.routines.UrlValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

class UrlValidationTests {

    private final UrlValidator urlValidator = new UrlValidator(new RegexValidator(new String[] {"http", "https","((localhost)(:[0-9]+))"}), 0);

    @ParameterizedTest
    @ValueSource(strings = {
        "www.test.com",
        "http:www.test.com",
        "test.com",
        "http://test..com",
    })
    @NullAndEmptySource
    @DisplayName("유효하지 않은 URL 테스트")
    void invalidParameterTest1(String url) {
        Assertions.assertFalse(urlValidator.isValid(url));
    }

    @ParameterizedTest
    @ValueSource(strings = {
        "http://www.test.com",
        "https://www.sub1.sub2.sub3.test.com",
        "https://www.sub1.sub2.sub3.test.co.kr?a=b&c=d&e=f&k=",
        "https://www.sub1.test.net",
        "http://localhost:8080"
    })
    @DisplayName("유효한 URL 테스트")
    void validParameterTest(String url) {
        Assertions.assertTrue(urlValidator.isValid(url));
    }
}
