package com.allsolved.allsolved.common.errorhandler;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class AllSolvedException extends RuntimeException{
    ErrorCode errorCode;
}
