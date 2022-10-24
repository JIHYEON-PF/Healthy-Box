package com.pf.healthybox.dto.orderInformationDto;

import java.time.LocalDateTime;

public record OiSubscribeBasketListDto (

    String basketNo,
    LocalDateTime createdAt,
    String subscribeCode,
    String subscribeName,
    int amount,
    LocalDateTime startDate,
    LocalDateTime endDate,
    String sellerCode

) {

    public static OiSubscribeBasketListDto of(String basketNo, LocalDateTime createdAt, String subscribeCode, String subscribeName, int amount, LocalDateTime startDate, LocalDateTime endDate, String sellerCode) {
        return new OiSubscribeBasketListDto(basketNo, createdAt, subscribeCode, subscribeName, amount, startDate, endDate, sellerCode);
    }

}