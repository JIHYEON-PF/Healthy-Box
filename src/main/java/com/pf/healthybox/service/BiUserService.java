package com.pf.healthybox.service;

import com.pf.healthybox.domain.baseInformation.BiUser;
import com.pf.healthybox.dto.baseInformationDto.BiUserDto;
import com.pf.healthybox.repository.BiUserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
            BiUser insertUser = dto.toEntity();
            insertUser.setCompName("");
            insertUser.setRecoCode(insertRecoCode());
            insertUser.setIsDel("N");
            insertUser.setAuthDiv("USE");
            insertUser.setAuthLevel("USE");

            if (insertUser.getNickname().isBlank()) {
                insertUser.setNickname(insertUser.getUserName());
            }

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
    @Transactional
    public BiUser showUserInfo(String userId) {
        return biUserRepository.findById(userId)
                .orElse(null);
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

    public BiUser login(String userId, String userPw) {
        return biUserRepository.findById(userId)
                .filter((data) -> data.getUserPw().equals(userPw))
                .orElse(null);
    }

    @Transactional
    public void modifyUserInfo(BiUserDto dto) {
        BiUser findUser = biUserRepository.getReferenceById(dto.userId());

        if (!dto.userPw().isBlank()) {
            findUser.setUserPw(dto.userPw());
        }
        findUser.setPhoneNumber(dto.phoneNumber());
        findUser.setUserName(dto.userName());
        findUser.setNickname(dto.nickname());
        findUser.setEmail(dto.email());
        findUser.setZipcode(dto.zipcode());
        findUser.setAddress1(dto.address1());
        findUser.setAddress2(dto.address2());

        biUserRepository.save(findUser);
    }
}
