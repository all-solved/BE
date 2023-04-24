package com.allsolved.allsolved.counter.controller;

import com.allsolved.allsolved.counter.dto.AlsoCounterDto;
import com.allsolved.allsolved.counter.service.AlsoCounterService;
import com.allsolved.allsolved.errorhandler.AllsoResponse;
import com.allsolved.allsolved.jwt.JwtTokenProvider;
import com.allsolved.allsolved.user.controller.JwtController;
import com.allsolved.allsolved.user.service.JwtService;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RequestMapping("/allso/m/counter")
@RestController
public class AlsoCounterController extends JwtController {
    private final AlsoCounterService alsoCounterService;

    public AlsoCounterController(JwtTokenProvider jwtTokenProvider, JwtService jwtService, AlsoCounterService alsoCounterService) {
        super(jwtTokenProvider, jwtService);
        this.alsoCounterService = alsoCounterService;
    }

    //소통창구 작성
    @PostMapping("/write")
    public AllsoResponse write(HttpServletRequest request, @RequestBody AlsoCounterDto alsoCounterDto) {
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
