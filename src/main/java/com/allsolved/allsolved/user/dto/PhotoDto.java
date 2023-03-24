package com.allsolved.allsolved.user.dto;

import com.allsolved.allsolved.user.entity.AlsoUser;
import com.allsolved.allsolved.user.entity.Photo;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.ManyToOne;

@Getter
@NoArgsConstructor
public class PhotoDto {

    @ManyToOne
    private AlsoUser alsoUser;
    private String type;
    private String uu_id;
    private String file_name;
    private String file_path;
    private String file_url;
    private String file_download_path;
    private Long file_size;

    @Builder
    public PhotoDto(AlsoUser alsoUser, String type, String uu_id, String file_name, String file_path, String file_url, String file_download_path, Long file_size) {
        this.alsoUser = alsoUser;
        this.type = type;
        this.uu_id = uu_id;
        this.file_name = file_name;
        this.file_path = file_path;
        this.file_url = file_url;
        this.file_download_path = file_download_path;
        this.file_size = file_size;
    }

    public Photo toEntity() {
        return Photo.builder()
                .alsoUser(alsoUser)
                .type(type)
                .uu_id(uu_id)
                .file_name(file_name)
                .file_path(file_path)
                .file_url(file_url)
                .file_download_path(file_download_path)
                .file_size(file_size)
                .build();
    }
}
