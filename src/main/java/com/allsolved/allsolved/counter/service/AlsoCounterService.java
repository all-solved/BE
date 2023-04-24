package com.allsolved.allsolved.counter.service;

import com.allsolved.allsolved.counter.dto.AlsoCounterDto;
import com.allsolved.allsolved.counter.entity.AlsoCounter;

public interface AlsoCounterService {

    AlsoCounter create(AlsoCounterDto alsoCounterDto);
    AlsoCounterDto getCounter(Long counterId);
}
