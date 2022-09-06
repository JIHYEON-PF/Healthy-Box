package com.pf.healthybox.dto.request.orderInformationReq;

import com.pf.healthybox.domain.config.DeliveryFlag;
import com.pf.healthybox.dto.orderInformationDto.OiDeliverDto;

public record OiDeliverRequest(
        Long idx,
        String userId,
        String deliveryName,
        String zipcode,
        String address1,
        String address2,
        DeliveryFlag deliveryFlag
) {

    public static OiDeliverRequest of(Long idx, String userId, String deliveryName, String zipcode, String address1, String address2, DeliveryFlag deliveryFlag) {
        return new OiDeliverRequest(idx, userId, deliveryName, zipcode, address1, address2, deliveryFlag);
    }

    // req to dto
    public OiDeliverDto toDto() {
        return OiDeliverDto.of(idx, userId, deliveryName, zipcode, address1, address2, deliveryFlag);
    }

}
