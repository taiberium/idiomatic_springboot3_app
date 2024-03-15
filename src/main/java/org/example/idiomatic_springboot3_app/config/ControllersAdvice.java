package org.example.idiomatic_springboot3_app.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ControllersAdvice {

    private static final Logger LOG = LoggerFactory.getLogger(ControllersAdvice.class);

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<ExceptionDto> handleValidationExceptions(Exception e) {
        exceptionErrorLog(e);
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ExceptionDto(e.getMessage()));
    }

    @ExceptionHandler(value = {Exception.class})
    public ResponseEntity<ExceptionDto> handleUnpredictedExceptions(Exception e) {
        exceptionErrorLog(e);
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ExceptionDto(e.getMessage()));
    }

    private void exceptionErrorLog(Exception e) {
        LOG.error(e.getMessage(), e);
    }

    public record ExceptionDto(String message) {
    }
}
