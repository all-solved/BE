package com.allsolved.allsolved.user.controller;

import com.allsolved.allsolved.errorhandler.AllsoResponse;
import com.allsolved.allsolved.jwt.JwtTokenProvider;
import com.allsolved.allsolved.user.dto.AlsoUserDto;
import com.allsolved.allsolved.user.entity.AlsoUser;
import com.allsolved.allsolved.user.service.AlsoUserService;
import com.allsolved.allsolved.user.service.JwtService;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/allso")
@AllArgsConstructor
@RestController
public class AlsoUserController {
    private final JwtTokenProvider jwtTokenProvider;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AlsoUserService alsoUserService;


    @PostMapping("/also")
    public AllsoResponse create(@RequestPart(value = "image", required = false) List<MultipartFile> files, @RequestPart(value = "alsoUserDto") AlsoUserDto alsoUserDto) throws Exception {
        alsoUserDto.setAlsoPassword(passwordEncoder.encode(alsoUserDto.getAlsoPassword()));
        return new AllsoResponse.ResponseMap(200, "data", alsoUserService.create(alsoUserDto, files));
    }

    @GetMapping("/login")
    public AllsoResponse login(HttpServletRequest request, @RequestParam("code") String code, @RequestHeader("User-Agent") String userAgent) {
        return jwtService.login(request, code, userAgent);
    }

    //아래는 연습
    @PutMapping("/m/update/{idx}")
    public Long update(@PathVariable Long idx, @RequestBody AlsoUserDto alsoUserDto) {
        return alsoUserService.update(idx, alsoUserDto);
    }

    @GetMapping("/m/{idx}")
    public AlsoUserDto searchByIdx(@PathVariable Long idx) {
        return alsoUserService.searchById(idx);
    }

    @GetMapping("/m")
    public List<AlsoUser> searchAllDesc() {
        return alsoUserService.searchAllDesc();
    }

    @GetMapping("/server/restore")
    public String restoreServer() { return "서버 복구 젠킨스 테스트 2TRY"; }


}
