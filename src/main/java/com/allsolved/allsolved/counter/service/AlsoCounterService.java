package com.allsolved.allsolved.counter.service;

import com.allsolved.allsolved.counter.dto.AlsoCounterDto;
import com.allsolved.allsolved.counter.dto.AlsoCounterListDto;
import com.allsolved.allsolved.counter.entity.AlsoCounter;

import java.util.List;

public interface AlsoCounterService {

    AlsoCounter create(String currentUserId, AlsoCounterDto alsoCounterDto);
    AlsoCounterDto getCounter(Long counterId);
    List<AlsoCounterListDto> getCounterList(String currentUserId);
}
