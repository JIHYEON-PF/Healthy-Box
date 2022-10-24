package com.pf.healthybox.dto.orderInformationDto;

import com.pf.healthybox.domain.orderInformation.OiSubscribeBasket;

import java.time.LocalDateTime;

public record OiSubscribeBasketDto(
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

    public static OiSubscribeBasketDto of(String basketNo, String userId, String subscribeCode, String productCode, String sellerCode, int productIdx, int qty, LocalDateTime deliveryDate, LocalDateTime createdAt) {
        return new OiSubscribeBasketDto(basketNo, userId, subscribeCode, productCode, sellerCode, productIdx, qty, deliveryDate, createdAt);
    }

    // dto >> entity
    public OiSubscribeBasket toEntity() {
        return OiSubscribeBasket.of(basketNo, userId, subscribeCode, productCode, sellerCode, productIdx, qty, deliveryDate);
    }

    // entity >> dto
    public static OiSubscribeBasketDto from(OiSubscribeBasket entity) {
        return OiSubscribeBasketDto.of(entity.getBasketNo(), entity.getUserId(), entity.getSubscribeCode(), entity.getProductCode(), entity.getSellerCode(), entity.getProductIdx(), entity.getQty(), entity.getDeliveryDate(), entity.getCreatedAt());
    }

}
