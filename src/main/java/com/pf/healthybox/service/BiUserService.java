package com.pf.healthybox.service;

import com.pf.healthybox.domain.baseInformation.BiUser;
import com.pf.healthybox.dto.baseInformationDto.BiUserDto;
import com.pf.healthybox.dto.response.baseInformationRes.BiUserResponse;
import com.pf.healthybox.repository.BiUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class BiUserService {

    private final BiUserRepository biUserRepository;


    public BiUserService(BiUserRepository biUserRepository) {
        this.biUserRepository = biUserRepository;
    }

    //회원가입 메서드
    public void signUp(BiUserDto dto) {
        String userId = dto.userId();

        Optional<BiUser> checkUser = biUserRepository.findById(userId);

        if (checkUser.isEmpty()) {
            biUserRepository.save(dto.toEntity());
        } else {
            throw new RuntimeException("UserInfo is exists");
        }

    }

    //회원 탈퇴 메서드
    public void deleteUser(String userId, String userPw) {
        biUserRepository.deleteByUserIdAndUserPw(userId, userPw);
    }

    //사용자 정보를 조회하는 메서드
    public BiUserResponse showUserInfo(String userId) {
        return BiUserResponse.from(BiUserDto.from(biUserRepository.getReferenceById(userId)));
    }

    //회원가입 - ID 중복검사 메서드
    public Boolean confirmId(String userId) {
        return biUserRepository.existsByUserId(userId);
    }

}
