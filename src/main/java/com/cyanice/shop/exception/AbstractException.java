package com.cyanice.shop.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public abstract class AbstractException extends RuntimeException {
    @Getter
    protected HttpStatus status;
    @Getter
    protected String code;
    public AbstractException(String message) {
        super(message);
    }
}
