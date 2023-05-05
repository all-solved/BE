package com.allsolved.allsolved.problem.entity;

import com.allsolved.allsolved.baseTime.BaseTimeEntity;
import com.allsolved.allsolved.counter.entity.AlsoCounter;
import com.allsolved.allsolved.problem.dto.AlsoProblemDto;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "also_problem")
public class AlsoProblem extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "problem_id", unique = true, nullable = false)
    private Long problemId;

    @ManyToOne
    @JoinColumn(name = "counter_id")
    @JsonBackReference
    private AlsoCounter alsoCounter;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "category")
    private String category;

    @Column(name = "importance_count")
    private int importanceCount;

    @Column(name = "email")
    private String email;

    @Column(name = "phone")
    private String phone;

//    @Column(name = "Problem_Image")
//    private String ProblemImage;

    @Builder
    public AlsoProblem(String title, String content, String category, int importanceCount, String email, String phone) {
        this.title = title;
        this.content = content;
        this.category = category;
        this.importanceCount = importanceCount;
        this.email = email;
        this.phone = phone;
    }

    public AlsoProblemDto toDto() {
        return AlsoProblemDto.builder()
                .title(title)
                .content(content)
                .category(category)
                .importanceCount(importanceCount)
                .email(email)
                .phone(phone)
                .counterId(alsoCounter.getCounterId()).build();
    }

    public void setAlsoCounter(AlsoCounter alsoCounter) { this.alsoCounter = alsoCounter ;}
}
