package com.pf.healthybox.dto.response.orderInformationRes;

import com.pf.healthybox.domain.config.DeliveryFlag;
import com.pf.healthybox.dto.orderInformationDto.OiDeliverDto;

public record OiDeliverResponse(
        Long idx,
        String userId,
        String deliveryName,
        String zipcode,
        String address1,
        String address2,
        DeliveryFlag deliveryFlag
) {

    public static OiDeliverResponse of(Long idx, String userId, String deliveryName, String zipcode, String address1, String address2, DeliveryFlag deliveryFlag) {
        return new OiDeliverResponse(idx, userId, deliveryName, zipcode, address1, address2, deliveryFlag);
    }

    public static OiDeliverResponse of(String deliveryName, DeliveryFlag deliveryFlag) {
        return new OiDeliverResponse(0L, "", deliveryName, "", "", "", deliveryFlag);
    }

    // res to dto
    public OiDeliverDto toDto(OiDeliverResponse res) {
        return OiDeliverDto.of(res.idx, res.userId, res.deliveryName, res.zipcode, res.address1, res.address2, res.deliveryFlag);
    }

    // dto from res
    public static OiDeliverResponse from(OiDeliverDto dto) {
        return new OiDeliverResponse(
                 dto.idx(), dto.userId(), dto.deliveryName(), dto.zipcode(), dto.address1(), dto.address2(), dto.deliveryFlag()
        );
    }
}
