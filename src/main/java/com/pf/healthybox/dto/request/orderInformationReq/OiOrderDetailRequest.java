package com.pf.healthybox.dto.request.orderInformationReq;

import com.pf.healthybox.domain.orderInformation.OiOrder;
import com.pf.healthybox.dto.orderInformationDto.OiOrderDetailDto;

import java.time.LocalDateTime;

public record OiOrderDetailRequest(
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

    public static OiOrderDetailRequest of(LocalDateTime createdAt, String orderNo, int orderIdx, String status, String productCode, String productName, int unitCost, int qty, int dcCost, int amount, String deliveryComp, String deliveryCode, String deliveryName, String zipcode, String address1, String address2, String payMethod, int deliveryCost, int totalDcCost, int totalAmount, String cardName, int quota, LocalDateTime deliveryDate) {
        return new OiOrderDetailRequest(createdAt, orderNo, orderIdx, status, productCode, productName, unitCost, qty, dcCost, amount, deliveryComp, deliveryCode, deliveryName, zipcode, address1, address2, payMethod, deliveryCost, totalDcCost, totalAmount, cardName, quota, deliveryDate);
    }

    // dto >> req
    public static OiOrderDetailRequest from(OiOrderDetailDto dto) {
        return OiOrderDetailRequest.of(dto.createdAt(), dto.orderNo(), dto.orderIdx(), dto.status(), dto.productCode(), dto.productName(), dto.unitCost(), dto.qty(), dto.dcCost(), dto.amount(), dto.deliveryComp(), dto.deliveryCode(), dto.deliveryName(), dto.zipcode(), dto.address1(), dto.address2(), dto.payMethod(), dto.deliveryCost(), dto.totalDcCost(), dto.totalAmount(), dto.cardName(), dto.quota(), dto.deliveryDate());
    }

    // req >> dto
    public OiOrderDetailDto toDto() {
        return OiOrderDetailDto.of(createdAt, orderNo, orderIdx, status, productCode, productName, unitCost, qty, dcCost, amount, deliveryComp, deliveryCode, deliveryName, zipcode, address1, address2, payMethod, deliveryCost, totalDcCost, totalAmount, cardName, quota, deliveryDate);
    }
}
