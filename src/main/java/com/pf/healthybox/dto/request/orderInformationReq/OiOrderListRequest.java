package com.pf.healthybox.dto.request.orderInformationReq;

import com.pf.healthybox.domain.config.Status;
import com.pf.healthybox.dto.orderInformationDto.OiOrderListDto;

import java.time.LocalDateTime;

public record OiOrderListRequest(
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

    public static OiOrderListRequest of(String orderNo, LocalDateTime createdAt, String productName, Long productCount, int amount, String status, String isSubscribe, String subscribeCode, LocalDateTime deliveryDate) {
        return new OiOrderListRequest(orderNo, createdAt, productName, productCount, amount, status, isSubscribe, subscribeCode, deliveryDate);
    }

    // req to dto
    public OiOrderListDto toDto() {
        return OiOrderListDto.of(
                orderNo, createdAt, productName, productCount, amount, status, isSubscribe, subscribeCode, deliveryDate
        );
    }

    // req from dto
    public static OiOrderListRequest from(OiOrderListDto dto) {
        return OiOrderListRequest.of(
                dto.orderNo(), dto.createdAt(), dto.productName(), dto.productCount(), dto.amount(), dto.status(), dto.isSubscribe(), dto.subscribeCode(), dto.deliveryDate()
        );
    }

}
