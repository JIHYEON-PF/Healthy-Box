package com.pf.healthybox.repository;

import com.pf.healthybox.domain.baseInformation.BiUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BiUserRepository extends JpaRepository<BiUser, String> {

    void deleteByUserIdAndUserPw(String userId, String userPw);
    boolean existsByUserId(String userId);
    boolean existsByRecoCode(String recoCode);

}
