package com.allsolved.allsolved.problem.repository;

import com.allsolved.allsolved.problem.entity.AlsoProblem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlsoProblemRepository extends JpaRepository<AlsoProblem, Long> {
    List<AlsoProblem> findByAlsoCounter_CounterIdOrderByCreatedDateDesc(Long counterId);
    Long countByAlsoCounter_CounterId(Long counterId);
}
