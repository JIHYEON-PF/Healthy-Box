package com.pf.healthybox.service;

import com.pf.healthybox.domain.baseInformation.BiUser;
import com.pf.healthybox.dto.baseInformationDto.BiUserDto;
import com.pf.healthybox.dto.response.baseInformationRes.BiUserResponse;
import com.pf.healthybox.repository.BiUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

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
            BiUser insertUser = dto.toEntity();
            insertUser.setCompName("");
            insertUser.setRecoCode(insertRecoCode());
            insertUser.setIsDel("N");
            insertUser.setAuthDiv("USE");
            insertUser.setAuthLevel("USE");
            biUserRepository.save(insertUser);
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

    //추천인 등록 메서드
    public String insertRecoCode() {

        StringBuilder buf = new StringBuilder();

        // 추천코드 자동생성( 1~3자리 숫자는 알파벳 대문자로 구성, 4~8자리는 랜덤한 숫자로 구성되도록)
        while (true) {
            buf.setLength(0);

            for (int i = 0 ; i < 4 ; i++) {
                if (i <= 2) {
                    buf.append((char) ((int) (Math.random() * 26) + 65));
                } else {
                    buf.append(String.format("%05d", (int) (Math.random()*99999)+1));
                }
            }

            if (!biUserRepository.existsByRecoCode(buf.toString())) {
                return buf.toString();
            }
        }
    }

}
