package com.cyanice.shop.exception;

import org.springframework.http.HttpStatus;

public class NoDataException extends AbstractException {
    public NoDataException() {
        super("No data found for the duration");
        this.status = HttpStatus.NOT_FOUND;
        this.code = "app.no-data";
    }
}
