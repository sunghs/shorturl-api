package sunghs.shorturl.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.security.SecureRandom;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestConstructor;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;
import sunghs.shorturl.api.model.OriginalUrlRequestDto;
import sunghs.shorturl.api.model.OriginalUrlResponseDto;
import sunghs.shorturl.api.model.ShortUrlComponent;
import sunghs.shorturl.api.model.ShortUrlRequestDto;
import sunghs.shorturl.api.model.ShortUrlResponseDto;

@ActiveProfiles("local")
@AutoConfigureMockMvc
@SpringBootTest
@TestConstructor(autowireMode = TestConstructor.AutowireMode.ALL)
@RequiredArgsConstructor
class IntegrationTests {

    private final MockMvc mockMvc;

    private final ObjectMapper objectMapper;

    private final ShortUrlComponent shortUrlComponent;

    private ShortUrlRequestDto shortUrlRequestDto = new ShortUrlRequestDto();

    private SecureRandom secureRandom = new SecureRandom();

    @BeforeEach
    void setUp() {
        // 중복되는 URL로 테스트 실패 가능성을 고려해 랜덤으로 파라미터를 추가
        shortUrlRequestDto.setOriginalUrl("https://www.test.com?a=b&c=d&e=f&q=" + secureRandom.nextInt(9999));
    }

    @Test
    @Transactional
    @DisplayName("단축URL -> 원본URL -> redirect 테스트")
    void integrationTest() throws Exception {
        // 단축 URL 요청
        // given & when
        String shortUrlResponse = mockMvc.perform(MockMvcRequestBuilders.post("/url/short")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(shortUrlRequestDto)))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andReturn()
            .getResponse()
            .getContentAsString();

        ShortUrlResponseDto shortUrlResponseDto = objectMapper.readValue(shortUrlResponse, ShortUrlResponseDto.class);

        // then
        Assertions.assertTrue(StringUtils.isNotEmpty(shortUrlResponseDto.getShortUrl()));
        Assertions.assertEquals(1, shortUrlResponseDto.getRequestCount());

        // 원본 URL 요청
        // given
        OriginalUrlRequestDto originalUrlRequestDto = new OriginalUrlRequestDto();
        originalUrlRequestDto.setShortUrl(shortUrlResponseDto.getShortUrl());

        // when
        String originalUrlResponse = mockMvc.perform(MockMvcRequestBuilders.post("/url/original")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(originalUrlRequestDto)))
            .andExpect(MockMvcResultMatchers.status().isOk())
            .andReturn()
            .getResponse()
            .getContentAsString();

        OriginalUrlResponseDto originalUrlResponseDto = objectMapper.readValue(originalUrlResponse, OriginalUrlResponseDto.class);

        // then
        Assertions.assertEquals(shortUrlRequestDto.getOriginalUrl(), originalUrlResponseDto.getOriginalUrl());
        Assertions.assertEquals(2, originalUrlResponseDto.getRequestCount());

        // 단축 URL redirect 요청
        mockMvc.perform(MockMvcRequestBuilders.get("/" + removePrefixUrl(shortUrlResponseDto.getShortUrl())))
            .andExpect(MockMvcResultMatchers.redirectedUrl(originalUrlResponseDto.getOriginalUrl()));
    }

    String removePrefixUrl(String url) {
        return url.replaceFirst(shortUrlComponent.getPrefixUrl(), "");
    }
}
