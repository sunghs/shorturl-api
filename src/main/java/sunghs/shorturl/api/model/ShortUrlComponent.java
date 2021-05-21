package sunghs.shorturl.api.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ShortUrlComponent {

    private String prefixUrl;

    private String characterList;

    private long validationTime;

    private int limitedCharacterSize;
}
