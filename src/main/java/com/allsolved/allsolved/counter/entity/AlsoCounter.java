package com.allsolved.allsolved.counter.entity;

import com.allsolved.allsolved.baseTime.BaseTimeEntity;
import com.allsolved.allsolved.counter.dto.AlsoCounterDto;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "also_counter")
public class AlsoCounter extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "counter_id", unique = true, nullable = false)
    private Long counterId;

    @Column(name = "title")
    private String title;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "content")
    private String content;

    @Column(name = "importance_count")
    private int importanceCount;

    @Column(name = "QRcode")
    private String QRcode;

    @Column(name = "isSolved")
    private boolean isSolved;

    @Column(name = "limited_date")
    private LocalDateTime limitedDate;

    @Builder
    public AlsoCounter(String title, String phone, String email, String content, int importanceCount, String QRcode, boolean isSolved, LocalDateTime limitedDate) {
        this.title = title;
        this.phone = phone;
        this.email = email;
        this.content = content;
        this.importanceCount = importanceCount;
        this.QRcode = QRcode;
        this.isSolved = isSolved;
        this.limitedDate = limitedDate;
    }

    public AlsoCounterDto toDto() {
        return AlsoCounterDto.builder()
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
