package com.allsolved.allsolved.user.repository;

import com.allsolved.allsolved.user.entity.AlsoUser;
import com.allsolved.allsolved.user.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByKeyId(String keyId);
    void deleteByKeyId(String keyId);

}
