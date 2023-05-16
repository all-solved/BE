package com.allsolved.allsolved.counter.controller;

import com.allsolved.allsolved.common.security.AuthController;
import com.allsolved.allsolved.counter.dto.AlsoCounterDto;
import com.allsolved.allsolved.counter.service.AlsoCounterService;
import com.allsolved.allsolved.common.errorhandler.AllsoResponse;
import com.allsolved.allsolved.common.jwt.JwtTokenProvider;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/allso/m/counter")
@AllArgsConstructor
@RestController
public class AlsoCounterController extends AuthController {
    private final JwtTokenProvider jwtTokenProvider;
    private final AlsoCounterService alsoCounterService;

    //소통창구 작성
    @PostMapping("/write")
    public AllsoResponse write(@RequestBody AlsoCounterDto alsoCounterDto) throws Exception {
        return new AllsoResponse.ResponseMap(200, "data", alsoCounterService.create(getCurrentUserId(), alsoCounterDto));
    }

    //소통창구 상세조회
    @GetMapping("/{counterId}")
    public AllsoResponse getCounter(@PathVariable Long counterId, Pageable pageable) {
        return new AllsoResponse.ResponseMap(200, "data", alsoCounterService.getCounter(pageable, counterId));
    }

    //소통창구 목록조회
    @GetMapping("/list")
    public AllsoResponse getCounterList(Pageable pageable) {
        return new AllsoResponse.ResponseMap(200, "data", alsoCounterService.getCounterList(pageable, getCurrentUserId()));
    }

    //소통창구 변경
    @PutMapping("/update/{counterId}")
    public AllsoResponse update(@PathVariable Long counterIdx) {
        
        return null;
    }

    //소통창구 삭제
    @DeleteMapping("/delete/{counterId}")
    public AllsoResponse delete(@PathVariable Long counterIdx) {

        return null;
    }



}
