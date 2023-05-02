package com.allsolved.allsolved.counter.controller;

import com.allsolved.allsolved.counter.dto.AlsoCounterDto;
import com.allsolved.allsolved.counter.service.AlsoCounterService;
import com.allsolved.allsolved.errorhandler.AllsoResponse;
import com.allsolved.allsolved.jwt.JwtTokenProvider;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/allso/m/counter")
@AllArgsConstructor
@RestController
public class AlsoCounterController {
    private final JwtTokenProvider jwtTokenProvider;
    private final AlsoCounterService alsoCounterService;

    //소통창구 작성
    @PostMapping("/write")
    public AllsoResponse write(HttpServletRequest request, @RequestBody AlsoCounterDto alsoCounterDto) throws Exception {
        return new AllsoResponse.ResponseMap(200, "data", alsoCounterService.create(request, alsoCounterDto));
    }

    //소통창구 상세조회
    @GetMapping("/{counterId}")
    public AllsoResponse getCounter(@PathVariable Long counterId) {
        return new AllsoResponse.ResponseMap(200, "data", alsoCounterService.getCounter(counterId));
    }

    //소통창구 목록조회
    @GetMapping("/list")
    public AllsoResponse getCounterList() {
        
        return null;
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
