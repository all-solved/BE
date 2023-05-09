package com.allsolved.allsolved.common.errorhandler;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Data
@Builder
public class ErrorResponseEntity {
    private int code;
    private String message;
    private HttpStatus status;

    public static ResponseEntity<ErrorResponseEntity> toResponseEntity(ErrorCode e) {
        return ResponseEntity
                .status(e.getStatus())
                .body(ErrorResponseEntity.builder()
                        .status(e.getStatus())
                        .code(e.getCode())
                        .message(e.getMessage())
                        .build());
    }
}
