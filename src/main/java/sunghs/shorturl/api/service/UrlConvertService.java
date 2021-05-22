package sunghs.shorturl.api.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sunghs.shorturl.api.exception.CharacterNotFoundException;
import sunghs.shorturl.api.exception.SequenceOverFlowException;
import sunghs.shorturl.api.exception.handler.ExceptionCodeManager;
import sunghs.shorturl.api.model.ShortUrlComponent;

import javax.annotation.PostConstruct;
import java.util.Arrays;

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

    /**
     * sequence 를 키로 하여 문자열을 생성합니다.
     *
     * @param seq long sequence
     * @return String ShortUrl
     */
    public String convertSeqToUrl(long seq) {
        if (seq >= limitedCount) {
            throw new SequenceOverFlowException(ExceptionCodeManager.SEQUENCE_OVERFLOW, limitedCount - 1);
        }

        StringBuffer result = new StringBuffer();

        while (seq > 0) {
            result.append(characterArray[((int) (seq % characterArray.length))]);
            seq /= characterArray.length;
        }

        log.info("convert {} to {}", seq, result);
        return result.toString();
    }

    /**
     * 문자열을 기반으로 sequence로 생성합니다.
     *
     * @param shortUrl shortUrl
     * @return long sequence
     */
    public long convertUrlToSeq(String shortUrl) {
        char[] unfoldUrl = shortUrl.toCharArray();
        long result = 0;

        for (int i = 0; i < unfoldUrl.length; i++) {
            result += search(unfoldUrl[i]) * (Math.pow(characterArray.length, i));
        }

        log.info("convert {} to {}", shortUrl, result);
        return result;
    }

    private int search(char c) {
        try {
            return Arrays.binarySearch(characterArray, c);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new CharacterNotFoundException(ExceptionCodeManager.CHARACTER_NOT_FOUND, e.getLocalizedMessage());
        }
    }
}
