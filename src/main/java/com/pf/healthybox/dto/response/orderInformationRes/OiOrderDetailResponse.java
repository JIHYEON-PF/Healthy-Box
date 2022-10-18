package com.pf.healthybox.dto.response.orderInformationRes;

import com.pf.healthybox.dto.orderInformationDto.OiOrderDetailDto;

import java.time.LocalDateTime;

public record OiOrderDetailResponse(
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

    public static OiOrderDetailResponse of(LocalDateTime createdAt, String orderNo, int orderIdx, String status, String productCode, String productName, int unitCost, int qty, int dcCost, int amount, String deliveryComp, String deliveryCode, String deliveryName, String zipcode, String address1, String address2, String payMethod, int deliveryCost, int totalDcCost, int totalAmount, String cardName, int quota, LocalDateTime deliveryDate) {
        return new OiOrderDetailResponse(createdAt, orderNo, orderIdx, status, productCode, productName, unitCost, qty, dcCost, amount, deliveryComp, deliveryCode, deliveryName, zipcode, address1, address2, payMethod, deliveryCost, totalDcCost, totalAmount, cardName, quota, deliveryDate);
    }

    //res to dto
    public OiOrderDetailDto toDto() {
        return OiOrderDetailDto.of(
                createdAt, orderNo, orderIdx, status, productCode, productName, unitCost, qty, dcCost, amount, deliveryComp, deliveryCode, deliveryName, zipcode, address1, address2, payMethod, deliveryCost, totalDcCost, totalAmount, cardName, quota, deliveryDate
        );
    }

    //res from dto
    public static OiOrderDetailResponse from(OiOrderDetailDto dto) {
        return new OiOrderDetailResponse(
                dto.createdAt(), dto.orderNo(), dto.orderIdx(), dto.status(), dto.productCode(), dto.productName(), dto.unitCost(), dto.qty(), dto.dcCost(), dto.amount(), dto.deliveryComp(), dto.deliveryCode(), dto.deliveryName(), dto.zipcode(), dto.address1(), dto.address2(), dto.payMethod(), dto.deliveryCost(), dto.totalDcCost(), dto.totalAmount(), dto.cardName(), dto.quota(), dto.deliveryDate()
        );
    }

}
