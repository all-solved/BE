package com.allsolved.allsolved.user.repository;

import com.allsolved.allsolved.user.dto.AlsoUserDto;
import com.allsolved.allsolved.user.entity.AlsoUser;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface AlsoUserRepository extends JpaRepository<AlsoUser, Long> {
    List<AlsoUser> findAllByOrderByUserIdDesc();
    Optional<AlsoUser> findByAlsoEmail(String alsoEmail);
}
