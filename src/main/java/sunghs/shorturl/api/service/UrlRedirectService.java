package sunghs.shorturl.api.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sunghs.shorturl.api.exception.PageNotFoundException;
import sunghs.shorturl.api.exception.ShortUrlNotFoundException;
import sunghs.shorturl.api.exception.handler.ExceptionCodeManager;
import sunghs.shorturl.api.model.entity.ShortUrlInfo;
import sunghs.shorturl.api.repository.ShortUrlInfoRepository;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class UrlRedirectService {

    private final UrlConvertService urlConvertService;

    private final ShortUrlInfoRepository shortUrlInfoRepository;

    /**
     * 단축 URL로 원본 URL을 반환합니다.
     *
     * @param shortUrl shortUrl
     * @return String OriginalUrl
     */
    @Transactional(rollbackFor = Exception.class)
    public String getOriginalUrl(final String shortUrl) {
        long seq = urlConvertService.convertUrlToSeq(shortUrl);

        try {
            ShortUrlInfo shortUrlInfo = shortUrlInfoRepository.findBySeqAndExpireDtGreaterThan(seq, LocalDateTime.now())
                .orElseThrow(() -> new ShortUrlNotFoundException(ExceptionCodeManager.SHORT_URL_NOT_FOUND));

            shortUrlInfo.addRequestCount();
            shortUrlInfoRepository.save(shortUrlInfo);

            return shortUrlInfo.getOriginalUrl();
        } catch(ShortUrlNotFoundException e) {
            throw new PageNotFoundException(ExceptionCodeManager.REDIRECT_ERROR);
        }
    }
}
