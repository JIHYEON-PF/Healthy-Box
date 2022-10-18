package com.pf.healthybox.dto.orderInformationDto;

import java.time.LocalDateTime;

public record OiOrderSubscribeListDto(
        String orderNo,
        LocalDateTime createdAt,
        int amount,
        String status,
        String subscribeName,
        LocalDateTime startDate,
        LocalDateTime endDate
) {

    public static OiOrderSubscribeListDto of(String orderNo, LocalDateTime createdAt, int amount, String status, String subscribeName, LocalDateTime startDate, LocalDateTime endDate) {
        return new OiOrderSubscribeListDto(orderNo, createdAt, amount, status, subscribeName, startDate, endDate);
    }
}
