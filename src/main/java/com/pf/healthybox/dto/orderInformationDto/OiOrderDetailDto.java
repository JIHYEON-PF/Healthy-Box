package com.pf.healthybox.dto.orderInformationDto;

import com.pf.healthybox.dto.response.orderInformationRes.OiOrderDetailResponse;

import java.time.LocalDateTime;

public record OiOrderDetailDto (
        LocalDateTime createdAt,
        String orderNo,
        int orderIdx,
        String status,
        String productCode,
        String productName,
        int unitCost,
        int qty,
        int dcCost,
        int amount,
        String deliveryComp,
        String deliveryCode,
        String deliveryName,
        String zipcode,
        String address1,
        String address2,
        String payMethod,
        int deliveryCost,
        int totalDcCost,
        int totalAmount,
        String cardName,
        int quota,
        LocalDateTime deliveryDate
) {

    public static OiOrderDetailDto of(LocalDateTime createdAt, String orderNo, int orderIdx, String status, String productCode, String productName, int unitCost, int qty, int dcCost, int amount, String deliveryComp, String deliveryCode, String deliveryName, String zipcode, String address1, String address2, String payMethod, int deliveryCost, int totalDcCost, int totalAmount, String cardName, int quota, LocalDateTime deliveryDate) {
        return new OiOrderDetailDto(createdAt, orderNo, orderIdx, status, productCode, productName, unitCost, qty, dcCost, amount, deliveryComp, deliveryCode, deliveryName, zipcode, address1, address2, payMethod, deliveryCost, totalDcCost, totalAmount, cardName, quota, deliveryDate);
    }

}
