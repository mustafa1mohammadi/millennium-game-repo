package com.example.demo.model.Exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class ErrorCode{

    @Getter
    private HttpStatus statusCode;

    //Internal Error Code for Debugging
    @Getter
    private String internalErrorCode;

    //Short Error Message
    @Getter
    private String  message;

    public ErrorCode (HttpStatus StatusCode, String InternalErrorCode, String Message)
    {
        this.statusCode = StatusCode;
        this.internalErrorCode = InternalErrorCode;
        this.message = Message;
    }

    @java.lang.Override
    public java.lang.String toString() {
        return "ErrorCode{" +
                "StatusCode=" + statusCode +
                ", InternalErrorCode='" + internalErrorCode + '\'' +
                ", Message='" + message+ '\'' +
                '}';
    }
}