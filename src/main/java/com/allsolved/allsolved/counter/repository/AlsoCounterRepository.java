package com.allsolved.allsolved.counter.repository;

import com.allsolved.allsolved.counter.dto.AlsoCounterDto;
import com.allsolved.allsolved.counter.entity.AlsoCounter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AlsoCounterRepository extends JpaRepository<AlsoCounter, Long> {
    Optional<AlsoCounterDto> findByCounterId(long counterId);
}
