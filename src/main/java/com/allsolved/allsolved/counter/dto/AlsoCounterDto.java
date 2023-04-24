package com.allsolved.allsolved.counter.dto;

import com.allsolved.allsolved.counter.entity.AlsoCounter;
import lombok.Builder;

import java.time.LocalDateTime;

public class AlsoCounterDto {

    private String title;
    private String phone;
    private String email;
    private String content;
    private int importanceCount;
    private String QRcode;
    private boolean isSolved;
    private LocalDateTime limitedDate;

    @Builder
    public AlsoCounterDto(String title, String phone, String email, String content, int importanceCount, String QRcode, boolean isSolved, LocalDateTime limitedDate) {
        this.title = title;
        this.phone = phone;
        this.email = email;
        this.content = content;
        this.importanceCount = importanceCount;
        this.QRcode = QRcode;
        this.isSolved = isSolved;
        this.limitedDate = limitedDate;
    }

    public AlsoCounter toEntity() {
        return AlsoCounter.builder()
                .title(title)
                .phone(phone)
                .email(email)
                .content(content)
                .importanceCount(importanceCount)
                .QRcode(QRcode)
                .isSolved(isSolved)
                .limitedDate(limitedDate).build();
    }
}
