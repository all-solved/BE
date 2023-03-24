package com.allsolved.allsolved.user.service;

import com.allsolved.allsolved.file.FileHandler;
import com.allsolved.allsolved.user.dto.AlsoUserDto;
import com.allsolved.allsolved.user.dto.Role;
import com.allsolved.allsolved.user.entity.AlsoUser;
import com.allsolved.allsolved.user.entity.Photo;
import com.allsolved.allsolved.user.repository.AlsoUserRepository;
import com.allsolved.allsolved.user.repository.PhotoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RequiredArgsConstructor
@Service
public class AlsoUserServiceImpl implements AlsoUserService, UserDetailsService {
    private final AlsoUserRepository alsoUserRepository;
    private final PhotoRepository photoRepository;
    private final FileHandler fileHandler;
    public static final String TYPE = "PROFILE";

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return alsoUserRepository.findByAlsoEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Email : " + username + " not found"));
    }

    @Transactional
    @Override
    public AlsoUser create(AlsoUserDto alsoUserDto, List<MultipartFile> files) {
        //role ENUM 체크 해주기
        AlsoUser alsoUser = alsoUserRepository.save(alsoUserDto.toEntity());
        Photo photo;
        try {
            for(MultipartFile file : files) {
                photo = null;
                photo = fileHandler.uploadFile(file, TYPE).toEntity();
                alsoUser.addPhoto(photo);
                photoRepository.save(photo);
            }
        } catch(IOException e) {
            e.printStackTrace();
        }
        return alsoUser;
    }

    @Transactional
    @Override
    public Long update(Long idx, AlsoUserDto alsoUserDto) {
        AlsoUser alsoUser = alsoUserRepository.findById(idx)
                .orElseThrow(() -> new IllegalArgumentException("해당 사용자가 존재하지 않습니다."));

        alsoUser.update(alsoUserDto.getAlsoName());

        return idx;
    }

    @Transactional(readOnly = true)
    @Override
    public AlsoUserDto searchById(Long id) {
        AlsoUser alsoUser = alsoUserRepository.findById(id).orElseThrow(() ->
                new IllegalArgumentException("해당 유저가 존재하지 않습니다."));

        AlsoUserDto alsoUserDto = AlsoUserDto.builder()
                .alsoEmail(alsoUser.getAlsoEmail())
                .alsoName(alsoUser.getAlsoName())
                .role(Role.from(alsoUser.getRole()))
                .photos(photoRepository.findByAlsoUserAndType(alsoUser, TYPE))
                .build();

        return alsoUserDto;

    }

    @Transactional
    @Override
    public List<AlsoUserDto> searchAllDesc() {
        return alsoUserRepository.findAllByOrderByUserIdDesc();
    }


}
