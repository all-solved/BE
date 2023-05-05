package com.allsolved.allsolved.problem.service;

import com.allsolved.allsolved.problem.dto.AlsoProblemDto;
import com.allsolved.allsolved.problem.entity.AlsoProblem;

public interface AlsoProblemService {
    AlsoProblemDto create(Long counterId, AlsoProblemDto alsoProblemDto);
}
