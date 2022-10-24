package com.pf.healthybox.dto.response.orderInformationRes;

import com.pf.healthybox.dto.orderInformationDto.OiSubscribeBasketListDto;

import java.time.LocalDateTime;

public record OiSubscribeBasketListResponse(

        String basketNo,
        LocalDateTime createdAt,
        String subscribeCode,
        String subscribeName,
        int amount,
        LocalDateTime startDate,
        LocalDateTime endDate,
        String sellerCode

) {

    public static OiSubscribeBasketListResponse of(String basketNo, LocalDateTime createdAt, String subscribeCode, String subscribeName, int amount, LocalDateTime startDate, LocalDateTime endDate, String sellerCode) {
        return new OiSubscribeBasketListResponse(basketNo, createdAt, subscribeCode, subscribeName, amount, startDate, endDate, sellerCode);
    }

    // req >> dto
    public OiSubscribeBasketListDto toDto() {
        return OiSubscribeBasketListDto.of(basketNo, createdAt, subscribeCode, subscribeName, amount, startDate, endDate, sellerCode);
    }

    // dto >> req
    public static OiSubscribeBasketListResponse from(OiSubscribeBasketListDto dto) {
        return OiSubscribeBasketListResponse.of(dto.basketNo(), dto.createdAt(), dto.subscribeCode(), dto.subscribeName(), dto.amount(), dto.startDate(), dto.endDate(), dto.sellerCode());
    }

}
