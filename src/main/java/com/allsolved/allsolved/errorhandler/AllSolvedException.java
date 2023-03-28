package com.allsolved.allsolved.errorhandler;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AllSolvedException extends RuntimeException{
    ErrorCode errorCode;
}
