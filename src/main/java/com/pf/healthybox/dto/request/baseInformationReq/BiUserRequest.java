package com.pf.healthybox.dto.request.baseInformationReq;

import com.pf.healthybox.dto.baseInformationDto.BiUserDto;

import java.io.Serializable;

public record BiUserRequest(
        String userId,
        String userPw,
        String authDiv,
        String authLevel,
        String userName,
        String nickname,
        String compName,
        String zipcode,
        String address1,
        String address2,
        String serialCode,
        String phoneNumber,
        String email,
        String recoCode,
        String isDel
) {

    public static BiUserRequest of(String userId, String userPw, String authDiv, String authLevel, String userName, String nickname, String compName, String zipcode, String address1, String address2, String serialCode, String phoneNumber, String email, String recoCode, String isDel) {
        return new BiUserRequest(userId, userPw, authDiv, authLevel, userName, nickname, compName, zipcode, address1, address2, serialCode, phoneNumber, email, recoCode, isDel);
    }

    // request >> dto
    public BiUserDto toDto() {
        return BiUserDto.of(userId, userPw, authDiv, authLevel, userName, nickname, compName, zipcode, address1, address2, serialCode, phoneNumber, email, recoCode, isDel);
    }

}
