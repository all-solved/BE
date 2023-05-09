package com.allsolved.allsolved.counter.dto;

import com.allsolved.allsolved.counter.entity.AlsoCounter;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
public class AlsoCounterListDto {
    private Long counterId;
    private String title;
    private String phone;
    private String email;
    private String content;
    private String QRcode;
    private boolean isSolved;
    private LocalDateTime limitedDate;
    private Long alsoProblemCount;

    @Builder
    public AlsoCounterListDto(Long counterId, String title, String phone, String email, String content, String QRcode, boolean isSolved, LocalDateTime limitedDate, Long alsoProblemCount) {
        this.counterId = counterId;
        this.title = title;
        this.phone = phone;
        this.email = email;
        this.content = content;
        this.QRcode = QRcode;
        this.isSolved = isSolved;
        this.limitedDate = limitedDate;
        this.alsoProblemCount = alsoProblemCount;
    }

    public AlsoCounter toEntity() {
        return AlsoCounter.builder()
                .counterId(counterId)
                .title(title)
                .phone(phone)
                .email(email)
                .content(content)
                .QRcode(QRcode)
                .isSolved(isSolved)
                .limitedDate(limitedDate).build();
    }
}
