package com.allsolved.allsolved.user.service;

import com.allsolved.allsolved.user.dto.AlsoUserDto;
import com.allsolved.allsolved.user.entity.AlsoUser;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface AlsoUserService {
    AlsoUser create(AlsoUserDto alsoUserDto, List<MultipartFile> files);
    AlsoUser snsLogin(String code, String userAgent);
    Long update(@PathVariable Long idx, @RequestBody AlsoUserDto alsoUserDto);
    AlsoUserDto searchById(@PathVariable Long id);
    List<AlsoUserDto> searchAllDesc();
}
