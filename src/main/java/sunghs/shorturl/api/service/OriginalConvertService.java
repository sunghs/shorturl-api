package sunghs.shorturl.api.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sunghs.shorturl.api.exception.ShortUrlNotFoundException;
import sunghs.shorturl.api.exception.handler.ExceptionCodeManager;
import sunghs.shorturl.api.model.OriginalUrlRequestDto;
import sunghs.shorturl.api.model.OriginalUrlResponseDto;
import sunghs.shorturl.api.model.entity.ShortUrlInfo;
import sunghs.shorturl.api.repository.ShortUrlInfoRepository;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class OriginalConvertService {

    private final ShortUrlInfoRepository shortUrlInfoRepository;

    /**
     * 단축 URL을 원본 URL 정보로 변환합니다.
     *
     * @param requestDto OriginalUrlRequestDto
     * @return OriginalUrlResponseDto
     */
    @Transactional(rollbackFor = Exception.class)
    public OriginalUrlResponseDto convert(final OriginalUrlRequestDto requestDto) {
        ShortUrlInfo shortUrlInfo = shortUrlInfoRepository.findByShortUrlAndExpireDtGreaterThan(
            requestDto.getShortUrl(), LocalDateTime.now()).orElseThrow(() -> new ShortUrlNotFoundException(ExceptionCodeManager.SHORT_URL_NOT_FOUND));

        shortUrlInfo.addRequestCount();
        shortUrlInfoRepository.save(shortUrlInfo);

        return OriginalUrlResponseDto.of(shortUrlInfo);
    }
}
