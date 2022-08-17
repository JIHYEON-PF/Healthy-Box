package com.pf.healthybox.dto.response.baseInformationRes;

import com.pf.healthybox.dto.baseInformationDto.BiUserDto;

import java.io.Serializable;

public record BiUserResponse(
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
        String recoCode,
        String isDel
) {

    public static BiUserResponse of(String userId, String userPw, String authDiv, String authLevel, String userName, String nickname, String compName, String zipcode, String address1, String address2, String serialCode, String phoneNumber, String recoCode, String isDel) {
        return new BiUserResponse(userId, userPw, authDiv, authLevel, userName, nickname, compName, zipcode, address1, address2, serialCode, phoneNumber, recoCode, isDel);
    }

    // dto >> response
    public static BiUserResponse from(BiUserDto dto) {
        return new BiUserResponse(dto.userId(), dto.userPw(), dto.authDiv(), dto.authLevel(), dto.userName(), dto.nickname(), dto.compName(), dto.zipcode(), dto.address1(), dto.address2(), dto.serialCode(), dto.phoneNumber(), dto.recoCode(), dto.isDel());
    }

}
