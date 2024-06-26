package com.cyanice.shop.exception;

import com.cyanice.shop.dto.ExceptionDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Throwable.class)
    public ResponseEntity<ExceptionDto> handleAll(Throwable t) {
        log.error(String.format("Exception: %s", t.getMessage()));
        return switch (t) {
            case AbstractException e -> ResponseEntity.status(e.status).body(
                    ExceptionDto.builder().code(e.code).message(e.getMessage()).build()
            );
            default -> ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    ExceptionDto.builder().code("app.unexpected").message(t.getMessage()).build()
            );
        };
    }
}
