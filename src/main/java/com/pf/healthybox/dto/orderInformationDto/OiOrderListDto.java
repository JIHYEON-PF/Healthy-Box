package com.pf.healthybox.dto.orderInformationDto;

import com.pf.healthybox.domain.config.Status;
import com.pf.healthybox.dto.request.orderInformationReq.OiOrderListRequest;

import java.time.LocalDateTime;

public record OiOrderListDto(
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

    public static OiOrderListDto of(String orderNo, LocalDateTime createdAt, String productName, Long productCount, int amount, String status, String isSubscribe, String subscribeCode, LocalDateTime deliveryDate) {
        return new OiOrderListDto(orderNo, createdAt, productName, productCount, amount, status, isSubscribe, subscribeCode, deliveryDate);
    }

}
