package com.allsolved.allsolved.problem.controller;

import com.allsolved.allsolved.common.errorhandler.AllsoResponse;
import com.allsolved.allsolved.problem.dto.AlsoProblemDto;
import com.allsolved.allsolved.problem.service.AlsoProblemService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/allso/u/problem")
@RequiredArgsConstructor
@RestController
public class AlsoProblemController {
    private final AlsoProblemService alsoProblemService;

    @PostMapping("/write/{counterId}")
    public AllsoResponse write(@PathVariable Long counterId, @RequestBody AlsoProblemDto alsoProblemDto) {
        return new AllsoResponse.ResponseMap(200, "data", alsoProblemService.create(counterId, alsoProblemDto));
    }

}
