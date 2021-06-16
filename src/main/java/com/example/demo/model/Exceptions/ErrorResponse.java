package com.example.demo.model.Exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse
{
    @Getter
    public ErrorCode errorCode;

    @Getter
    public String message;

    @Getter
    public String detail;
}