package com.pf.healthybox.dto.response.orderInformationRes;

import com.pf.healthybox.dto.orderInformationDto.OiSubscribeBasketDetailDto;

import java.time.LocalDateTime;

public record OiSubscribeBasketDetailResponse(
        String basketNo,
        LocalDateTime deliveryDate,
        String productCode,
        String productName,
        int productIdx,
        int qty,
        String sellerCode,
        String subscribeCode,
        String subscribeName,
        int price,
        int amount,
        LocalDateTime startDate,
        LocalDateTime endDate
) {

    public static OiSubscribeBasketDetailResponse of(String basketNo, LocalDateTime deliveryDate, String productCode, String productName, int productIdx, int qty, String sellerCode, String subscribeCode, String subscribeName, int price, int amount, LocalDateTime startDate, LocalDateTime endDate) {
        return new OiSubscribeBasketDetailResponse(basketNo, deliveryDate, productCode, productName, productIdx, qty, sellerCode, subscribeCode, subscribeName, price, amount, startDate, endDate);
    }

    // res >> dto
    public OiSubscribeBasketDetailDto toDto() {
        return OiSubscribeBasketDetailDto.of(basketNo, deliveryDate, productCode, productName, productIdx, qty, sellerCode, subscribeCode, subscribeName, price, amount, startDate, endDate);
    }

    // dto >>res
    public static OiSubscribeBasketDetailResponse from (OiSubscribeBasketDetailDto dto) {
        return OiSubscribeBasketDetailResponse.of(dto.basketNo(), dto.deliveryDate(), dto.productCode(), dto.productName(), dto.productIdx(), dto.qty(), dto.sellerCode(), dto.subscribeCode(), dto.subscribeName(), dto.price(), dto.amount(), dto.startDate(), dto.endDate());
    }

}
