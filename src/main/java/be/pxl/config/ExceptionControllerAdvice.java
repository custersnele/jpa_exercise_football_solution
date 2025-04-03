package be.pxl.config;

import be.pxl.api.dto.ApiError;
import be.pxl.exception.FootballException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler({
            FootballException.class
    })
    ResponseEntity<Object> handleFootballException(FootballException footballException) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ApiError(footballException.getMessage(), HttpStatus.BAD_REQUEST));

    }
}
