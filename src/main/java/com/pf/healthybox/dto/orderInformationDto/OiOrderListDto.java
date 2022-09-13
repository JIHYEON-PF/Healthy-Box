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
        String status
) {

    public static OiOrderListDto of(String orderNo, LocalDateTime createdAt, String productName, Long productCount, int amount, String status) {
        return new OiOrderListDto(orderNo, createdAt, productName, productCount, amount, status);
    }

    // dto >> req
    public OiOrderListRequest toReq() {
        return OiOrderListRequest.of(
                orderNo, createdAt, productName, productCount, amount, status
        );
    }


}
