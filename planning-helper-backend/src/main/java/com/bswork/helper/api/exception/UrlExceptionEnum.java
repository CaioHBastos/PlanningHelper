package com.bswork.helper.api.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
public enum UrlExceptionEnum {

    UNPROCESSABLE_ENTITY("/transacao-nao-permitida", HttpStatus.UNPROCESSABLE_ENTITY.value()),
    BAD_REQUEST("/campo-nao-informado", HttpStatus.BAD_REQUEST.value()),
    NOT_FOUND("/dado-nao-encontrado", HttpStatus.NOT_FOUND.value());

    private String url;
    private Integer httpStatus;

    public static String getUrlForException(Integer httpStatusValue) {
        if (Objects.nonNull(httpStatusValue)) {
            return Arrays.stream(values())
                    .filter(httpValue -> httpValue.getHttpStatus().equals(httpStatusValue))
                    .map(UrlExceptionEnum::getUrl)
                    .collect(Collectors.joining());
        }

        return "";
    }
}
