package com.pf.healthybox.dto.orderInformationDto;

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
        int amount,
        PayMethod payMethod,
        String apiCode
) {

    public static OiOrderDto of(String orderNo, int orderIdx, Status status, String userId, Long deliverIdx, String productCode, String sellerCode, int qty, int unitCost, int amount, PayMethod payMethod, String apiCode) {
        return new OiOrderDto(orderNo, orderIdx, status, userId, deliverIdx, productCode, sellerCode, qty, unitCost, amount, payMethod, apiCode);
    }

    // dto > entity
    public OiOrder toEntity() {
        return OiOrder.of(orderNo, orderIdx, status, userId, deliverIdx, productCode, sellerCode, qty, unitCost, amount, payMethod, apiCode);
    }

    //entity > dto
    public static OiOrderDto from(OiOrder entity) {
        return new OiOrderDto(entity.getOrderNo(), entity.getOrderIdx(), entity.getStatus(), entity.getUserId(), entity.getDeliverIdx(), entity.getProductCode(), entity.getSellerCode(), entity.getQty(), entity.getUnitCost(), entity.getAmount(), entity.getPayMethod(), entity.getApiCode());
    }

}
