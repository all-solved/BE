package com.allsolved.allsolved.counter.service;

import com.allsolved.allsolved.counter.dto.AlsoCounterDto;
import com.allsolved.allsolved.counter.entity.AlsoCounter;
import com.allsolved.allsolved.counter.repository.AlsoCounterRepository;
import com.allsolved.allsolved.errorhandler.AllSolvedException;
import com.allsolved.allsolved.errorhandler.ErrorCode;
import com.allsolved.allsolved.problem.repository.AlsoProblemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@Service
public class AlsoCounterServiceImpl implements AlsoCounterService {
    private final AlsoCounterRepository alsoCounterRepository;
    private final AlsoProblemRepository alsoProblemRepository;

    @Transactional
    @Override
    public AlsoCounter create(HttpServletRequest request, AlsoCounterDto alsoCounterDto) {
        AlsoCounter alsoCounter = alsoCounterRepository.save(alsoCounterDto.toEntity());
        //Todo : DynamicUpdate로 바꾸기

        return alsoCounterRepository.save(alsoCounter);
    }

    @Override
    public AlsoCounterDto getCounter(Long counterId) {
        AlsoCounter alsoCounter = alsoCounterRepository.findByCounterId(counterId)
                .orElseThrow(() -> new AllSolvedException(ErrorCode.NOTFOUND));
        alsoCounter.setAlsoProblems(alsoProblemRepository.findByAlsoCounter_CounterIdOrderByCreatedDateDesc(counterId));
        return alsoCounter.toDto();
    }

    private String getQRcodeURI(HttpServletRequest request, Long uri) {
        StringBuffer sb = new StringBuffer();
        sb.append(request.getScheme());
        sb.append("://");
        sb.append(request.getServerName());
        sb.append(":");
        sb.append(request.getServerPort());
        sb.append("/allso/u/problem/write/");
        sb.append(uri);
        return sb.toString();
    }
}
