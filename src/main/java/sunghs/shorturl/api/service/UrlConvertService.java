package sunghs.shorturl.api.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sunghs.shorturl.api.exception.SequenceOverFlowException;
import sunghs.shorturl.api.model.ShortUrlComponent;

import javax.annotation.PostConstruct;

@Slf4j
@Service
@RequiredArgsConstructor
public class UrlConvertService {

    private final ShortUrlComponent shortUrlComponent;

    private char[] characterArray;

    private long limitedCount;

    @PostConstruct
    public void initialize() {
        characterArray = shortUrlComponent.getCharacterList().toCharArray();
        limitedCount = (long) Math.pow(shortUrlComponent.getCharacterList().length(), shortUrlComponent.getLimitedCharacterSize());
    }

    public String convertSeqToUrl(long seq) {
        if (seq >= limitedCount) {
            throw new SequenceOverFlowException();
        }

        StringBuffer result = new StringBuffer(shortUrlComponent.getPrefixUrl());

        while (seq > 0) {
            result.append(characterArray[((int) (seq % characterArray.length))]);
            seq /= characterArray.length;
        }

        log.info("convert {} to {}", seq, result);
        return result.toString();
    }

    public long convertUrlToSeq(String shortUrl) {
        String replacedUrl = shortUrl.trim().replace(shortUrlComponent.getPrefixUrl(), "");
        char[] unfoldUrl = replacedUrl.toCharArray();
        long result = 0;

        for (int i = 0; i < replacedUrl.length(); i++) {
            result += characterArray[unfoldUrl[i]] * (characterArray.length ^ i);
        }

        log.info("convert {} to {}", shortUrl, result);
        return result;
    }
}
