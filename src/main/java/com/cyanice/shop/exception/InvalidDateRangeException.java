package com.cyanice.shop.exception;

import org.springframework.http.HttpStatus;

public class InvalidDateRangeException extends AbstractException {
    public InvalidDateRangeException() {
        super("The date range provided is invalid");
        this.status = HttpStatus.BAD_REQUEST;
        this.code = "app.invalid-date-range";
    }
}
