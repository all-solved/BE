package com.allsolved.allsolved.user.controller;

import com.allsolved.allsolved.errorhandler.AllsoResponse;
import com.allsolved.allsolved.file.FileHandler;
import com.allsolved.allsolved.jwt.JwtTokenProvider;
import com.allsolved.allsolved.user.dto.AlsoUserDto;
import com.allsolved.allsolved.user.entity.AlsoUser;
import com.allsolved.allsolved.user.service.AlsoUserService;
import com.allsolved.allsolved.user.service.JwtService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.jni.File;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RequestMapping("/allso")
@RestController
public class AlsoUserController extends JwtController{
    private final PasswordEncoder passwordEncoder;
    private final AlsoUserService alsoUserService;

    public AlsoUserController(JwtTokenProvider jwtTokenProvider, JwtService jwtService, PasswordEncoder passwordEncoder, AlsoUserService alsoUserService) {
        super(jwtTokenProvider, jwtService);
        this.passwordEncoder = passwordEncoder;
        this.alsoUserService = alsoUserService;
    }

    @PostMapping("/also")
    public AllsoResponse create(@RequestPart(value = "image", required = false) List<MultipartFile> files, @RequestPart(value = "alsoUserDto") AlsoUserDto alsoUserDto) throws Exception {
        alsoUserDto.setAlsoPassword(passwordEncoder.encode(alsoUserDto.getAlsoPassword()));
        return new AllsoResponse.ResponseMap(200, "data", alsoUserService.create(alsoUserDto, files));
    }

//    @PostMapping("/login")
//    public AllsoResponse login(HttpServletRequest request, @RequestBody Map<String, String> user, @RequestHeader("User-Agent") String userAgent) {
//        return getJwtService().login(request, user, userAgent);
//    }

    @GetMapping("/sns/login")
    public AllsoResponse login(HttpServletRequest request, @RequestParam("code") String code, @RequestHeader("User-Agent") String userAgent) {
        return getJwtService().login(request, code, userAgent);
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

    @GetMapping("/server/restore")
    public String restoreServer() { return "서버 복구 젠킨스 테스트"; }


}
