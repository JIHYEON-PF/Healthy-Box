package com.pf.healthybox.dto.request.orderInformationReq;

import com.pf.healthybox.dto.orderInformationDto.OiOrderStatusContentDto;

public record OiOrderStatusContentRequest(
        String orderNo,
        String userId,
        String sellerCode,
        String division,
        String title,
        String content
) {

    public static OiOrderStatusContentRequest of(String orderNo, String userId, String sellerCode, String division, String title, String content) {
        return new OiOrderStatusContentRequest(orderNo, userId, sellerCode, division, title, content);
    }

    // req to dto
    public OiOrderStatusContentDto toDto() {
        return OiOrderStatusContentDto.of(orderNo, userId, sellerCode, division, title, content);
    }

    // req from dto
    public static OiOrderStatusContentRequest from(OiOrderStatusContentDto dto) {
        return OiOrderStatusContentRequest.of(dto.orderNo(), dto.userId(), dto.sellerCode(), dto.division(), dto.title(), dto.content());
    }
}
