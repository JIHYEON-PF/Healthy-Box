package com.pf.healthybox.dto.orderInformationDto;

import com.pf.healthybox.domain.config.DeliveryFlag;
import com.pf.healthybox.domain.orderInformation.OiDeliver;

public record OiDeliverDto(
        Long idx,
        String userId,
        String deliveryName,
        String zipcode,
        String address1,
        String address2,
        DeliveryFlag deliveryFlag
) {

    public static OiDeliverDto of(Long idx, String userId, String deliveryName, String zipcode, String address1, String address2, DeliveryFlag deliveryFlag) {
        return new OiDeliverDto(idx, userId, deliveryName, zipcode, address1, address2, deliveryFlag);
    }

    public static OiDeliverDto of(String userId, String deliveryName, String zipcode, String address1, String address2, DeliveryFlag deliveryFlag) {
        return new OiDeliverDto(null, userId, deliveryName, zipcode, address1, address2, deliveryFlag);
    }

    // dto >> entity
    public OiDeliver toEntity() {
        return OiDeliver.of(userId, deliveryName, zipcode, address1, address2, deliveryFlag);
    }

    //entity >> dto
    public static OiDeliverDto from(OiDeliver entity) {
        return new OiDeliverDto(entity.getIdx(), entity.getUserId(), entity.getDeliveryName(), entity.getZipcode(), entity.getAddress1(), entity.getAddress2(), entity.getDeliveryFlag());
    }
}
