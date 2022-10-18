package com.pf.healthybox.dto.orderInformationDto;

import com.pf.healthybox.domain.orderInformation.OiOrderStatusContent;

public record OiOrderStatusContentDto(
        String orderNo,
        String userId,
        String sellerCode,
        String division,
        String title,
        String content
) {

    public static OiOrderStatusContentDto of(String orderNo, String userId, String sellerCode, String division, String title, String content) {
        return new OiOrderStatusContentDto(orderNo, userId, sellerCode, division, title, content);
    }

    // dto to entity
    public OiOrderStatusContent toEntity() {
        return OiOrderStatusContent.of(orderNo, userId, sellerCode, division, title, content);
    }

    // dto from entity
    public static OiOrderStatusContentDto from(OiOrderStatusContent entity) {
        return OiOrderStatusContentDto.of(entity.getOrderNo(), entity.getUserId(), entity.getSellerCode(), entity.getDivision(), entity.getTitle(), entity.getContent());
    }
}
