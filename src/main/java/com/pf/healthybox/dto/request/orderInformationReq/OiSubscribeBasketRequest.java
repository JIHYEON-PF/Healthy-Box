package com.pf.healthybox.dto.request.orderInformationReq;

import com.pf.healthybox.dto.orderInformationDto.OiSubscribeBasketDto;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

public record OiSubscribeBasketRequest(
        String basketNo,
        String userId,
        String subscribeCode,
        String productCode,
        String sellerCode,
        int productIdx,
        int qty,
        @DateTimeFormat(pattern = "yyyy-MM-dd kk:mm:ss")
        LocalDateTime deliveryDate
) {

    public static OiSubscribeBasketRequest of(String basketNo, String userId, String subscribeCode, String productCode, String sellerCode, int productIdx, int qty, LocalDateTime deliveryDate) {
        return new OiSubscribeBasketRequest(basketNo, userId, subscribeCode, productCode, sellerCode, productIdx, qty, deliveryDate);
    }

    // req >> dto
    public OiSubscribeBasketDto toDto() {
        return OiSubscribeBasketDto.of(basketNo, userId, subscribeCode, productCode, sellerCode, productIdx, qty, deliveryDate);
    }

    // dto >> req
    public static OiSubscribeBasketRequest from(OiSubscribeBasketDto dto) {
        return OiSubscribeBasketRequest.of(dto.basketNo(), dto.userId(), dto.subscribeCode(), dto.productCode(), dto.sellerCode(), dto.productIdx(), dto.qty(), dto.deliveryDate());
    }

}
