package com.allsolved.allsolved.user.dto;

import com.allsolved.allsolved.user.entity.AlsoUser;
import com.allsolved.allsolved.user.entity.Photo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class AlsoUserDto {

    private String alsoEmail;
    private String alsoName;
    private Role role;
    private List<Photo> photos;

    @Builder
    public AlsoUserDto(String alsoEmail, String alsoName, Role role, List<Photo> photos) {
        this.alsoEmail = alsoEmail;
        this.alsoName = alsoName;
        this.role = role;
        this.photos = photos;
    }

    public AlsoUser toEntity() {
        return AlsoUser.builder()
                .alsoEmail(alsoEmail)
                .alsoName(alsoName)
                .role(role.getValue())
                .build();
    }
}
