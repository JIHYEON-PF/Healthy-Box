package com.pf.healthybox.dto.orderInformationDto;

import com.pf.healthybox.domain.config.DeliveryComp;
import com.pf.healthybox.domain.config.PayMethod;
import com.pf.healthybox.domain.config.Status;
import com.pf.healthybox.domain.orderInformation.OiOrder;

public record OiOrderDto(
        String orderNo,
        int orderIdx,
        Status status,
        String userId,
        Long deliverIdx,
        String productCode,
        String sellerCode,
        int qty,
        int unitCost,
        int dcCost,
        int deliveryCost,
        int amount,
        PayMethod payMethod,
        String apiCode,
        DeliveryComp deliveryComp,
        String deliveryCode
) {

    public static OiOrderDto of(String orderNo, int orderIdx, Status status, String userId, Long deliverIdx, String productCode, String sellerCode, int qty, int unitCost, int dcCost, int deliveryCost, int amount, PayMethod payMethod, String apiCode, DeliveryComp deliveryComp, String deliveryCode) {
        return new OiOrderDto(orderNo, orderIdx, status, userId, deliverIdx, productCode, sellerCode, qty, unitCost, dcCost, deliveryCost, amount, payMethod, apiCode, deliveryComp, deliveryCode);
    }

    // dto > entity
    public OiOrder toEntity() {
        return OiOrder.of(orderNo, orderIdx, status, userId, deliverIdx, productCode, sellerCode, qty, unitCost, dcCost, deliveryCost, amount, payMethod, apiCode, deliveryComp, deliveryCode);
    }

    //entity > dto
    public static OiOrderDto from(OiOrder entity) {
        return new OiOrderDto(entity.getOrderNo(), entity.getOrderIdx(), entity.getStatus(), entity.getUserId(), entity.getDeliverIdx(), entity.getProductCode(), entity.getSellerCode(), entity.getQty(), entity.getUnitCost(), entity.getDcCost(), entity.getDeliveryCost(), entity.getAmount(), entity.getPayMethod(), entity.getApiCode(), entity.getDeliveryComp(), entity.getDeliveryCode());
    }

}
