package com.pf.healthybox.dto.orderInformationDto;

import com.pf.healthybox.domain.config.DeliveryComp;
import com.pf.healthybox.domain.config.PayMethod;
import com.pf.healthybox.domain.config.Status;
import com.pf.healthybox.domain.orderInformation.OiOrder;

import java.time.LocalDateTime;

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
        String deliveryComp,
        String deliveryCode,
        String cardName,
        String cardNum,
        int quota,
        String isSubscribe,
        String subscribeCode,
        LocalDateTime deliveryDate

) {

    public static OiOrderDto of(String orderNo, int orderIdx, Status status, String userId, Long deliverIdx, String productCode, String sellerCode, int qty, int unitCost, int dcCost, int deliveryCost, int amount, PayMethod payMethod, String apiCode, String deliveryComp, String deliveryCode, String cardName, String cardNum, int quota, String isSubscribe, String subscribeCode, LocalDateTime deliveryDate) {
        return new OiOrderDto(orderNo, orderIdx, status, userId, deliverIdx, productCode, sellerCode, qty, unitCost, dcCost, deliveryCost, amount, payMethod, apiCode, deliveryComp, deliveryCode, cardName, cardNum, quota, isSubscribe, subscribeCode, deliveryDate);
    }

    // dto > entity
    public OiOrder toEntity() {
        return OiOrder.of(orderNo, orderIdx, status, userId, deliverIdx, productCode, sellerCode, qty, unitCost, dcCost, deliveryCost, amount, payMethod, apiCode, deliveryComp, deliveryCode, cardName, cardNum, quota, isSubscribe, subscribeCode, deliveryDate);
    }

    //entity > dto
    public static OiOrderDto from(OiOrder entity) {
        return new OiOrderDto(entity.getOrderNo(), entity.getOrderIdx(), entity.getStatus(), entity.getUserId(), entity.getDeliverIdx(), entity.getProductCode(), entity.getSellerCode(), entity.getQty(), entity.getUnitCost(), entity.getDcCost(), entity.getDeliveryCost(), entity.getAmount(), entity.getPayMethod(), entity.getApiCode(), entity.getDeliveryComp(), entity.getDeliveryCode(), entity.getCardName(), entity.getCardNum(), entity.getQuota(), entity.getIsSubscribe(), entity.getSubscribeCode(), entity.getDeliveryDate());
    }

}
