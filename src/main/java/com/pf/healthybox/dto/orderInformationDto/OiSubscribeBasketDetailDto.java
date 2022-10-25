package com.pf.healthybox.dto.orderInformationDto;

import java.time.LocalDateTime;

public record OiSubscribeBasketDetailDto(
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

    public static OiSubscribeBasketDetailDto of(String basketNo, LocalDateTime deliveryDate, String productCode, String productName, int productIdx, int qty, String sellerCode, String subscribeCode, String subscribeName, int price, int amount, LocalDateTime startDate, LocalDateTime endDate) {
        return new OiSubscribeBasketDetailDto(basketNo, deliveryDate, productCode, productName, productIdx, qty, sellerCode, subscribeCode, subscribeName, price, amount, startDate, endDate);
    }
}
