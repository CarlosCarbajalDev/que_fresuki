package com.example.que_fresuki.exceptions.ingredientExceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ResponseStatus(HttpStatus.CONFLICT)
public class InvalidIngredientExceptions extends RuntimeException{
    private String message;
    private int code;
    private HttpStatus http;
}
