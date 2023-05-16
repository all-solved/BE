package com.allsolved.allsolved.counter.repository;

import com.allsolved.allsolved.counter.entity.AlsoCounter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AlsoCounterRepository extends JpaRepository<AlsoCounter, Long> {
    Optional<AlsoCounter> findByCounterId(long counterId);
    Page<AlsoCounter> findByAlsoEmailOrderByCreatedDateAsc(Pageable pageable, String alsoEmail);
}
