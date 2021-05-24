package sunghs.shorturl.api.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.UrlValidator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sunghs.shorturl.api.exception.InvalidUrlException;
import sunghs.shorturl.api.exception.handler.ExceptionCodeManager;
import sunghs.shorturl.api.model.ShortUrlComponent;
import sunghs.shorturl.api.model.ShortUrlRequestDto;
import sunghs.shorturl.api.model.ShortUrlResponseDto;
import sunghs.shorturl.api.model.entity.ShortUrlInfo;
import sunghs.shorturl.api.repository.ShortUrlInfoRepository;

import java.time.LocalDateTime;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ShortConvertService {

    private final ShortUrlComponent shortUrlComponent;

    private final UrlConvertService urlConvertService;

    private final ShortUrlInfoRepository shortUrlInfoRepository;

    private final UrlValidator urlValidator;

    public String getPrefixUrl() {
        return shortUrlComponent.getPrefixUrl();
    }

    public LocalDateTime getValidationTime() {
        return LocalDateTime.now().plusDays(shortUrlComponent.getValidationDays());
    }

    /**
     * 원본 URL을 단축 URL로 변환합니다.
     *
     * @param requestDto ShortUrlRequestDto
     * @return ShortUrlResponseDto
     */
    @Transactional(rollbackFor = Exception.class)
    public ShortUrlResponseDto convert(final ShortUrlRequestDto requestDto) {
        if (!urlValidator.isValid(requestDto.getOriginalUrl())) {
            throw new InvalidUrlException(ExceptionCodeManager.INVALID_URL);
        }

        Optional<ShortUrlInfo> optionalShortUrlInfo = shortUrlInfoRepository.findByOriginalUrlAndExpireDtGreaterThan(
            requestDto.getOriginalUrl(), LocalDateTime.now());

        final ShortUrlInfo shortUrlInfo = optionalShortUrlInfo.orElseGet(() -> ShortUrlInfo.builder()
            .originalUrl(requestDto.getOriginalUrl())
            .requestCount(1)
            .expireDt(getValidationTime())
            .build());

        if (StringUtils.isNotEmpty(shortUrlInfo.getShortUrl())) {
            shortUrlInfo.addRequestCount();
        } else {
            shortUrlInfoRepository.save(shortUrlInfo);
            shortUrlInfo.setShortUrl(getPrefixUrl() + urlConvertService.convertSeqToUrl(shortUrlInfo.getSeq()));
        }

        shortUrlInfoRepository.save(shortUrlInfo);

        return ShortUrlResponseDto.of(shortUrlInfo);
    }
}
