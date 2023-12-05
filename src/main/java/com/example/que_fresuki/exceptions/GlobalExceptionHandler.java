package com.example.que_fresuki.exceptions;

import com.example.que_fresuki.api.ApiResponseException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundProductExceptions.class)
    public ResponseEntity<ApiResponseException> notFoundProductExeptionsException(NotFoundProductExceptions productExeptions) {
        ApiResponseException apiResponseException = new ApiResponseException(productExeptions.getMessage(), productExeptions.getCode(), productExeptions.getHttp());
        return ResponseEntity.status(productExeptions.getHttp()).body(apiResponseException);
    }

    @ExceptionHandler(InvalidProductExceptions.class)
    public ResponseEntity<ApiResponseException> invalidProductEx( InvalidProductExceptions exeptions){
        ApiResponseException apiResponseException = new ApiResponseException(exeptions.getMessage(), exeptions.getCode(), exeptions.getHttp());
        return ResponseEntity.status(exeptions.getHttp()).body(apiResponseException);
    }
}
