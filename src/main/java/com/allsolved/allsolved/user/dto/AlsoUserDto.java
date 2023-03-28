package com.allsolved.allsolved.user.dto;

import com.allsolved.allsolved.user.entity.AlsoUser;
import com.allsolved.allsolved.user.entity.Photo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@NoArgsConstructor
public class AlsoUserDto {

    private String alsoEmail;
    private String alsoPassword;
    private String alsoName;
    private Role role;
    private List<Photo> photos;

    @Builder
    public AlsoUserDto(String alsoEmail, String alsoPassword, String alsoName, Role role, List<Photo> photos) {
        this.alsoEmail = alsoEmail;
        this.alsoPassword = alsoPassword;
        this.alsoName = alsoName;
        this.role = role;
        this.photos = photos;
    }

    public AlsoUser toEntity() {
        return AlsoUser.builder()
                .alsoEmail(alsoEmail)
                .alsoPassword(alsoPassword)
                .alsoName(alsoName)
                .role(role.getValue())
                .build();
    }
}
