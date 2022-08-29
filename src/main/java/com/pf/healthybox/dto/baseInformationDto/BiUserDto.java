package com.pf.healthybox.dto.baseInformationDto;

import com.pf.healthybox.domain.baseInformation.BiUser;

import java.io.Serializable;
import java.time.LocalDateTime;

public record BiUserDto(
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
        String isDel,
        LocalDateTime createdAt,
//        String createdBy,
        LocalDateTime updatedAt
//        String updatedBy
) {

    public static BiUserDto of(String userId, String userPw, String authDiv, String authLevel, String userName, String nickname, String compName, String zipcode, String address1, String address2, String serialCode, String phoneNumber, String email, String recoCode, String isDel) {
        return new BiUserDto(userId, userPw, authDiv, authLevel, userName, nickname, compName, zipcode, address1, address2, serialCode, phoneNumber, email, recoCode, isDel, null, null);
    }

    public static BiUserDto of(String userId, String userPw, String authDiv, String authLevel, String userName, String nickname, String compName, String zipcode, String address1, String address2, String serialCode, String phoneNumber, String email, String recoCode, String isDel, LocalDateTime createdAt, LocalDateTime updatedAt) {
        return new BiUserDto(userId, userPw, authDiv, authLevel, userName, nickname, compName, zipcode, address1, address2, serialCode, phoneNumber, email, recoCode, isDel, createdAt, updatedAt);
    }

    // entity >> dto
    public static BiUserDto from(BiUser entity) {

        String nickname = entity.getNickname();

        if (nickname == null || nickname.isBlank()) {
            nickname = entity.getUserName();
        }

        return new BiUserDto(
                entity.getUserId(), entity.getUserPw(), entity.getAuthDiv(), entity.getAuthLevel(), entity.getUserName(), nickname, entity.getCompName(), entity.getZipcode(), entity.getAddress1(), entity.getAddress2(), entity.getSerialCode(), entity.getPhoneNumber(), entity.getEmail(), entity.getRecoCode(), entity.getIsDel(), entity.getCreatedAt(), entity.getUpdatedAt()
        );
    }

    //dto >> entity

    public BiUser toEntity() {
        return BiUser.of(userId, userPw, authDiv, authLevel, userName, nickname, compName, zipcode, address1, address2, serialCode, phoneNumber, email, recoCode, isDel);
    }

}
