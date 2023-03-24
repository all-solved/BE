package com.allsolved.allsolved.user.repository;

import com.allsolved.allsolved.user.dto.PhotoDto;
import com.allsolved.allsolved.user.entity.AlsoUser;
import com.allsolved.allsolved.user.entity.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhotoRepository extends JpaRepository<Photo, Long> {
    List<Photo> findByAlsoUserAndType(AlsoUser alsoUser, String type);
}
