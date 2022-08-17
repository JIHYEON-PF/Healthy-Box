package com.pf.healthybox.service;

import com.pf.healthybox.domain.baseInformation.BiUser;
import com.pf.healthybox.dto.baseInformationDto.BiUserDto;
import com.pf.healthybox.repository.BiUserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@DisplayName("비즈니스 로직 - 회원")
@ExtendWith(MockitoExtension.class)
class BiUserServiceTest {

    @InjectMocks private BiUserService sut;

    @Mock private BiUserRepository biUserRepository;

    @DisplayName("1. 유저 정보를 입력하면 회원가입이 진행된다.")
    @Test
    void givenUserInfo_whenSavingUser_thenSavesUser() {
        //given
        BiUserDto dto = createUserDto();
        given(biUserRepository.save(any(BiUser.class))).willReturn(createUser());

        //when
        sut.signUp(dto);

        //then
        then(biUserRepository).should().save(dto.toEntity());
    }

    @DisplayName("2. 유저 삭제를 요청하면 회원 정보가 삭제된다.")
    @Test
    void givenUserIdAndUserPw_whenDeletingUser_thenDeletesUser() {
        //given
        String userId = "testId";
        String userPw = "testPw";
        willDoNothing().given(biUserRepository).deleteByUserIdAndUserPw(userId,userPw);

        //when
        sut.deleteUser(userId, userPw);

        //then
        then(biUserRepository).should().deleteByUserIdAndUserPw(userId,userPw);

    }

    @DisplayName("3. 회원의 아이디를 통해 회원정보를 가져온다")
    @Test
    void givenUserId_whenRequestingUserInfo_thenResponseUserInfo() {
        //given
        String userId = "testId";
        given(biUserRepository.getReferenceById(userId)).willReturn(createUser());

        //when
        sut.showUserInfo(userId);

        //then
        then(biUserRepository).should().getReferenceById(userId);

    }

    private BiUserDto createUserDto() {
        return BiUserDto.of(
            "testId", "testPw", "USE", "USE", "테스트", "테스트", "", "00000", "테스트 기본 주소", "테스트 상세 주소", "9001011111111", "01012341234", "ABC12345", "N"
        );
    }

    private BiUser createUser() {
        return BiUser.of(
                "testId", "testPw", "USE", "USE", "테스트", "테스트", "", "00000", "테스트 기본 주소", "테스트 상세 주소", "9001011111111", "01012341234", "ABC12345", "N"
        );
    }

}