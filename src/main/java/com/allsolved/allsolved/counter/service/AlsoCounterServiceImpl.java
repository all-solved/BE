package com.allsolved.allsolved.counter.service;

import com.allsolved.allsolved.counter.dto.AlsoCounterDto;
import com.allsolved.allsolved.counter.dto.AlsoCounterListDto;
import com.allsolved.allsolved.counter.entity.AlsoCounter;
import com.allsolved.allsolved.counter.repository.AlsoCounterRepository;
import com.allsolved.allsolved.common.errorhandler.AllSolvedException;
import com.allsolved.allsolved.common.errorhandler.ErrorCode;
import com.allsolved.allsolved.problem.repository.AlsoProblemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class AlsoCounterServiceImpl implements AlsoCounterService {
    private final AlsoCounterRepository alsoCounterRepository;
    private final AlsoProblemRepository alsoProblemRepository;

    @Transactional
    @Override
    public AlsoCounter create(String currentUserId, AlsoCounterDto alsoCounterDto) {
        AlsoCounter alsoCounter = alsoCounterDto.toEntity();
        alsoCounter.setAlsoEmail(currentUserId);
        //Todo : DynamicUpdate로 바꾸기

        return alsoCounterRepository.save(alsoCounter);
    }

    @Override
    public AlsoCounterDto getCounter(Pageable pageable, Long counterId) {
        AlsoCounter alsoCounter = alsoCounterRepository.findByCounterId(counterId)
                .orElseThrow(() -> new AllSolvedException(ErrorCode.NOTFOUND));
        alsoCounter.setAlsoProblems(alsoProblemRepository.findByAlsoCounter_CounterIdOrderByCreatedDateDesc(pageable, counterId).getContent());
        return alsoCounter.toDto();
    }

    @Override
    public List<AlsoCounterListDto> getCounterList(Pageable pageable, String currentUserId) {
        List<AlsoCounter> alsoCounterList = alsoCounterRepository.findByAlsoEmailOrderByCreatedDateAsc(pageable, currentUserId).getContent();
        //아래 코드가 Entity 타입을 Dto 타입으로 바꿔주는 함수식
        return alsoCounterList.stream()
                .map(m -> m.toListDto(alsoProblemRepository.countByAlsoCounter_CounterId(m.getCounterId())))
                .collect(Collectors.toList());
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
