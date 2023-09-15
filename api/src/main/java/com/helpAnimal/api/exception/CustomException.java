package com.helpAnimal.api.exception;

import org.springframework.http.HttpStatus;

public class CustomException extends RuntimeException{
    public CustomException(String mensagem, HttpStatus httpStatus){
        super(mensagem);
    }
}
