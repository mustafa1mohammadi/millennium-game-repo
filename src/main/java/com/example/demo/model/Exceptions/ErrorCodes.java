package com.example.demo.model.Exceptions;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

public class ErrorCodes {


    public static final ErrorCode INTERNAL_SERVER_ERROR = new ErrorCode(HttpStatus.INTERNAL_SERVER_ERROR, "101", "Internal Server Error");
    public static final ErrorCode BAD_REQUEST = new ErrorCode(HttpStatus.BAD_REQUEST, "102", "Bad Request");
    public static final ErrorCode NOT_FOUND = new ErrorCode(HttpStatus.NOT_FOUND, "105", "Not Found");
    public static final ErrorCode CONFLICT = new ErrorCode(HttpStatus.CONFLICT, "107", "Conflict");

}