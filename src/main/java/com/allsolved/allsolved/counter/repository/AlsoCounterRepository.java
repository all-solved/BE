package com.allsolved.allsolved.counter.repository;

import com.allsolved.allsolved.counter.entity.AlsoCounter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AlsoCounterRepository extends JpaRepository<AlsoCounter, Long> {
    Optional<AlsoCounter> findByCounterId(long counterId);
    List<AlsoCounter> findByAlsoEmailOrderByCreatedDateAsc(String alsoEmail);
}
