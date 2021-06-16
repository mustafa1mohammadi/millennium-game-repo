package com.example.demo.model.Exceptions;


import lombok.Getter;

public class GameException extends Throwable {

    @Getter
    private Exception innerException;

    @Getter
    private ErrorCode errorCode;

    @Getter
    private String description;

    public GameException(Exception innerException, ErrorCode errorCode, String description) {
        this.innerException = innerException;
        this.errorCode = errorCode;
        this.description = description;
    }

    public GameException(ErrorCode errorCode, String description) {
        this.errorCode = errorCode;
        this.description = description;
    }
}