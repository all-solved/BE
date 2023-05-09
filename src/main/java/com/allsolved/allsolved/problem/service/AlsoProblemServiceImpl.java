package com.allsolved.allsolved.problem.service;

import com.allsolved.allsolved.counter.entity.AlsoCounter;
import com.allsolved.allsolved.counter.repository.AlsoCounterRepository;
import com.allsolved.allsolved.common.errorhandler.AllSolvedException;
import com.allsolved.allsolved.common.errorhandler.ErrorCode;
import com.allsolved.allsolved.problem.dto.AlsoProblemDto;
import com.allsolved.allsolved.problem.entity.AlsoProblem;
import com.allsolved.allsolved.problem.repository.AlsoProblemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class AlsoProblemServiceImpl implements AlsoProblemService {
    private final AlsoProblemRepository alsoProblemRepository;
    private final AlsoCounterRepository alsoCounterRepository;

    @Override
    public AlsoProblemDto create(Long counterId, AlsoProblemDto alsoProblemDto) {
        AlsoCounter alsoCounter = alsoCounterRepository.findByCounterId(counterId)
                .orElseThrow(() -> new AllSolvedException(ErrorCode.NOTFOUND));

        AlsoProblem alsoProblem = alsoProblemDto.toEntity();
        alsoProblem.setAlsoCounter(alsoCounter);

        return alsoProblemRepository.save(alsoProblem).toDto();
    }
}
