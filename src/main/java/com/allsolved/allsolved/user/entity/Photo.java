package com.allsolved.allsolved.user.entity;

import com.allsolved.allsolved.baseTime.BaseTimeEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
@Table(name = "photo_of_also_user")
public class Photo extends BaseTimeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "photo_id", unique = true, nullable = false)
    private Long photo_id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private AlsoUser alsoUser;

    @Column
    private String type;

    @Column
    private String uu_id;

    @Column
    private String file_name;

    @Column
    private String file_path;

    @Column
    private String file_url;

    @Column
    private String file_download_path;

    @Column
    private Long file_size;

    @Builder
    public Photo(AlsoUser alsoUser, String type, String uu_id, String file_name, String file_path, String file_url, String file_download_path, Long file_size) {
        this.alsoUser = alsoUser;
        this.type = type;
        this.uu_id = uu_id;
        this.file_name = file_name;
        this.file_path = file_path;
        this.file_url = file_url;
        this.file_download_path = file_download_path;
        this.file_size = file_size;
    }
    public void setAlsoUser(AlsoUser alsoUser) {
        this.alsoUser = alsoUser;
    }


}
