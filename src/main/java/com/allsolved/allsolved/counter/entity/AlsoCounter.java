package com.allsolved.allsolved.counter.entity;

import com.allsolved.allsolved.baseTime.BaseTimeEntity;
import com.allsolved.allsolved.counter.dto.AlsoCounterDto;
import com.allsolved.allsolved.counter.dto.AlsoCounterListDto;
import com.allsolved.allsolved.problem.entity.AlsoProblem;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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

    @Column(name = "also_email")
    private String alsoEmail;

    @Column(name = "title")
    private String title;

    @Column(name = "phone")
    private String phone;

    @Column(name = "email")
    private String email;

    @Column(name = "content")
    private String content;

    @Column(name = "QRcode")
    private String QRcode;

    @Column(name = "isSolved")
    private boolean isSolved;

    @Column(name = "limited_date")
    private LocalDateTime limitedDate;

    @OneToMany(
            mappedBy = "alsoCounter",
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            orphanRemoval = true
    )
    @JsonManagedReference
    private List<AlsoProblem> alsoProblems = new ArrayList<>();

    @Builder
    public AlsoCounter(Long counterId, String title, String phone, String email, String content, String QRcode, boolean isSolved, LocalDateTime limitedDate, List<AlsoProblem> alsoProblems) {
        this.counterId = counterId;
        this.title = title;
        this.phone = phone;
        this.email = email;
        this.content = content;
        this.QRcode = QRcode;
        this.isSolved = isSolved;
        this.limitedDate = limitedDate;
        this.alsoProblems = alsoProblems;
    }

    public AlsoCounterDto toDto() {
        return AlsoCounterDto.builder()
                .counterId(counterId)
                .title(title)
                .phone(phone)
                .email(email)
                .content(content)
                .QRcode(QRcode)
                .isSolved(isSolved)
                .limitedDate(limitedDate)
                .alsoProblems(alsoProblems).build();
    }

    public AlsoCounterListDto toListDto(Long alsoProblemCount) {
        return AlsoCounterListDto.builder()
                .counterId(counterId)
                .title(title)
                .phone(phone)
                .email(email)
                .content(content)
                .QRcode(QRcode)
                .isSolved(isSolved)
                .limitedDate(limitedDate)
                .alsoProblemCount(alsoProblemCount).build();
    }

    public void addProblem(AlsoProblem alsoProblem) {
        this.alsoProblems.add(alsoProblem);
        alsoProblem.setAlsoCounter(this);
    }

}
