package com.pf.healthybox.dto.response.orderInformationRes;

import com.pf.healthybox.domain.config.Status;
import com.pf.healthybox.dto.orderInformationDto.OiOrderListDto;

import java.time.LocalDateTime;

public record OiOrderListResponse(
        String orderNo,
        LocalDateTime createdAt,
        String productName,
        Long productCount,
        int amount,
        String status,
        String isSubscribe,
        String subscribeCode,
        LocalDateTime deliveryDate
) {

    public static OiOrderListResponse of(String orderNo, LocalDateTime createdAt, String productName, Long productCount, int amount, String status, String isSubscribe, String subscribeCode, LocalDateTime deliveryDate) {
        return new OiOrderListResponse(orderNo, createdAt, productName, productCount, amount, status, isSubscribe, subscribeCode, deliveryDate);
    }

    // response to dto
    public OiOrderListDto toDto() {
        return OiOrderListDto.of(orderNo, createdAt, productName, productCount, amount, status, isSubscribe, subscribeCode, deliveryDate);
    }

    // response from dto
    public static OiOrderListResponse from(OiOrderListDto dto) {
        return new OiOrderListResponse(
                dto.orderNo(), dto.createdAt(), dto.productName(), dto.productCount(), dto.amount(), dto.status(), dto.isSubscribe(), dto.subscribeCode(), dto.deliveryDate()
        );
    }

}
