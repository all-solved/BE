package com.allsolved.allsolved.errorhandler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AllSolvedExceptionHandler {
    @ExceptionHandler(AllSolvedException.class)
    protected ResponseEntity<ErrorResponseEntity> handleCustomException(AllSolvedException e) {
        return ErrorResponseEntity.toResponseEntity(e.getErrorCode());
    }
}
