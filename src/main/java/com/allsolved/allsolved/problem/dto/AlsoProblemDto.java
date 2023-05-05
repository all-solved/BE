package com.allsolved.allsolved.problem.dto;

import com.allsolved.allsolved.problem.entity.AlsoProblem;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class AlsoProblemDto {
    private String title;
    private String content;
    private String category;
    private int importanceCount;
    private String email;
    private String phone;
    private Long counterId;

    @Builder
    public AlsoProblemDto(String title, String content, String category, int importanceCount, String email, String phone, Long counterId) {
        this.title = title;
        this.content = content;
        this.category = category;
        this.importanceCount = importanceCount;
        this.email = email;
        this.phone = phone;
        this.counterId = counterId;
    }

    public AlsoProblem toEntity() {
        return AlsoProblem.builder()
                .title(title)
                .content(content)
                .category(category)
                .importanceCount(importanceCount)
                .email(email)
                .phone(phone).build();
    }

}
