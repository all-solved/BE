package com.allsolved.allsolved.counter.service;

import com.allsolved.allsolved.counter.dto.AlsoCounterDto;
import com.allsolved.allsolved.counter.entity.AlsoCounter;

import javax.servlet.http.HttpServletRequest;

public interface AlsoCounterService {

    AlsoCounter create(HttpServletRequest request, AlsoCounterDto alsoCounterDto);
    AlsoCounterDto getCounter(Long counterId);
}
