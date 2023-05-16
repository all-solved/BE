package com.allsolved.allsolved.problem.repository;

import com.allsolved.allsolved.problem.entity.AlsoProblem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AlsoProblemRepository extends JpaRepository<AlsoProblem, Long> {
    Page<AlsoProblem> findByAlsoCounter_CounterIdOrderByCreatedDateDesc(Pageable pageable, Long counterId);
    Long countByAlsoCounter_CounterId(Long counterId);
}
