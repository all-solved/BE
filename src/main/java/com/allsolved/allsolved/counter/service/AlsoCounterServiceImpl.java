package com.allsolved.allsolved.counter.service;

import com.allsolved.allsolved.counter.dto.AlsoCounterDto;
import com.allsolved.allsolved.counter.entity.AlsoCounter;
import com.allsolved.allsolved.counter.repository.AlsoCounterRepository;
import com.allsolved.allsolved.errorhandler.AllSolvedException;
import com.allsolved.allsolved.errorhandler.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class AlsoCounterServiceImpl implements AlsoCounterService {
    private final AlsoCounterRepository alsoCounterRepository;

    @Transactional
    @Override
    public AlsoCounter create(AlsoCounterDto alsoCounterDto) {
        return alsoCounterRepository.save(alsoCounterDto.toEntity());
    }

    @Override
    public AlsoCounterDto getCounter(Long counterId) {
        return alsoCounterRepository.findByCounterId(counterId)
                .orElseThrow(() -> new AllSolvedException(ErrorCode.NOTFOUND));
    }
}
