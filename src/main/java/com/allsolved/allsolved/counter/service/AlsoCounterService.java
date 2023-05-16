package com.allsolved.allsolved.counter.service;

import com.allsolved.allsolved.counter.dto.AlsoCounterDto;
import com.allsolved.allsolved.counter.dto.AlsoCounterListDto;
import com.allsolved.allsolved.counter.entity.AlsoCounter;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AlsoCounterService {

    AlsoCounter create(String currentUserId, AlsoCounterDto alsoCounterDto);
    AlsoCounterDto getCounter(Pageable pageable, Long counterId);
    List<AlsoCounterListDto> getCounterList(Pageable pageable, String currentUserId);
}
