package com.pf.healthybox.dto.response.orderInformationRes;

import com.pf.healthybox.dto.orderInformationDto.OiOrderSubscribeListDto;

import java.time.LocalDateTime;

public record OiOrderSubscribeListResponse(
        String orderNo,
        LocalDateTime createdAt,
        int amount,
        String status,
        String subscribeName,
        LocalDateTime startDate,
        LocalDateTime endDate
) {

    public static OiOrderSubscribeListResponse of(String orderNo, LocalDateTime createdAt, int amount, String status, String subscribeName, LocalDateTime startDate, LocalDateTime endDate) {
        return new OiOrderSubscribeListResponse(orderNo, createdAt, amount, status, subscribeName, startDate, endDate);
    }

    // dto >> res
    public static OiOrderSubscribeListDto from(OiOrderSubscribeListDto dto) {
        return OiOrderSubscribeListDto.of(
                dto.orderNo(), dto.createdAt(), dto.amount(), dto.status(), dto.subscribeName(), dto.startDate(), dto.endDate()
        );
    }

    // res >> dto
    public OiOrderSubscribeListDto toDto() {
        return OiOrderSubscribeListDto.of(
                orderNo, createdAt, amount, status, subscribeName, startDate, endDate
        );
    }

}
