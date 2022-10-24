package com.pf.healthybox.dto.response.orderInformationRes;

import com.pf.healthybox.dto.orderInformationDto.OiSubscribeBasketDto;

import java.time.LocalDateTime;

public record OiSubscribeBasketResponse(
        String basketNo,
        String userId,
        String subscribeCode,
        String productCode,
        String sellerCode,
        int productIdx,
        int qty,
        LocalDateTime deliveryDate,
        LocalDateTime createdAt
) {

    public static OiSubscribeBasketResponse of(String basketNo, String userId, String subscribeCode, String productCode, String sellerCode, int productIdx, int qty, LocalDateTime deliveryDate, LocalDateTime createdAt) {
        return new OiSubscribeBasketResponse(basketNo, userId, subscribeCode, productCode, sellerCode, productIdx, qty, deliveryDate, createdAt);
    }

    // res >> dto
    public OiSubscribeBasketDto toDto() {
        return OiSubscribeBasketDto.of(basketNo, userId, subscribeCode, productCode, sellerCode, productIdx, qty, deliveryDate, createdAt);
    }

    // dto >>res
    public static OiSubscribeBasketResponse from(OiSubscribeBasketDto dto) {
        return OiSubscribeBasketResponse.of(dto.basketNo(), dto.userId(), dto.subscribeCode(), dto.productCode(), dto.sellerCode(), dto.productIdx(), dto.qty(), dto.deliveryDate(), dto.createdAt());
    }
}
