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

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@Service
public class AlsoCounterServiceImpl implements AlsoCounterService {
    private final AlsoCounterRepository alsoCounterRepository;

    @Transactional
    @Override
    public AlsoCounter create(HttpServletRequest request, AlsoCounterDto alsoCounterDto) {
        AlsoCounter alsoCounter = alsoCounterRepository.save(alsoCounterDto.toEntity());
        //Todo : DynamicUpdate로 바꾸기
        alsoCounter.setQRcode(getQRcodeURI(request, alsoCounter.getCounterId()));
        return alsoCounterRepository.save(alsoCounter);
    }

    @Override
    public AlsoCounterDto getCounter(Long counterId) {
        return alsoCounterRepository.findByCounterId(counterId)
                .orElseThrow(() -> new AllSolvedException(ErrorCode.NOTFOUND)).toDto();
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
