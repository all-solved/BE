package com.allsolved.allsolved.user.controller;

import com.allsolved.allsolved.file.FileHandler;
import com.allsolved.allsolved.user.dto.AlsoUserDto;
import com.allsolved.allsolved.user.entity.AlsoUser;
import com.allsolved.allsolved.user.service.AlsoUserService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.jni.File;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class AlsoUserController {
    private final AlsoUserService alsoUserService;

    @PostMapping("/also")
    public AlsoUser create(
            @RequestPart(value = "image", required = false) List<MultipartFile> files,
            @RequestPart(value = "alsoUserDto") AlsoUserDto alsoUserDto
    ) throws Exception {

        return alsoUserService.create(alsoUserDto, files);
    }

    @PutMapping("/also/update/{idx}")
    public Long update(@PathVariable Long idx, @RequestBody AlsoUserDto alsoUserDto) {
        return alsoUserService.update(idx, alsoUserDto);
    }

    @GetMapping("/also/{idx}")
    public AlsoUserDto searchByIdx(@PathVariable Long idx) {
        return alsoUserService.searchById(idx);
    }

    @GetMapping("/also")
    public List<AlsoUserDto> searchAllDesc() {
        return alsoUserService.searchAllDesc();
    }


}
