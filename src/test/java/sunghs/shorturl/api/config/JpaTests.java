package sunghs.shorturl.api.config;

import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestConstructor;
import sunghs.shorturl.api.model.entity.ShortUrlInfo;
import sunghs.shorturl.api.repository.ShortUrlInfoRepository;

@DataJpaTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@RequiredArgsConstructor
public class JpaTests {

    private final ShortUrlInfoRepository shortUrlInfoRepository;

    @Test
    @DisplayName("JPA 삽입 테스트")
    void jpaTest() {
        final ShortUrlInfo shortUrlInfo = ShortUrlInfo.builder()
            .originalUrl("original-url-test")
            .shortUrl("short-url-test")
            .requestCount(5)
            .createDt(LocalDateTime.now())
            .expireDt(LocalDateTime.now().plusDays(1))
            .build();

        shortUrlInfoRepository.save(shortUrlInfo);

        Assertions.assertTrue(shortUrlInfo.getSeq() > 0);
    }
}
