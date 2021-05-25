package sunghs.shorturl.api.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class ErrorResponseDto {

    private String errorCode;

    private String message;
}
