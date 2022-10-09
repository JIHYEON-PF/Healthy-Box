package com.pf.healthybox.dto.request.orderInformationReq;

import com.pf.healthybox.domain.config.PayMethod;
import com.pf.healthybox.domain.config.Status;
import com.pf.healthybox.domain.orderInformation.OiOrder;
import com.pf.healthybox.dto.orderInformationDto.OiOrderDto;

public record OiOrderRequest(
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
        String payMethod,
        String apiCode,
        String deliveryComp,
        String deliveryCode
) {

    public static OiOrderRequest of(String orderNo, int orderIdx, Status status, String userId, Long deliverIdx, String productCode, String sellerCode, int qty, int unitCost, int dcCost, int deliveryCost, int amount, String payMethod, String apiCode, String deliveryComp, String deliveryCode) {
        return new OiOrderRequest(orderNo, orderIdx, status, userId, deliverIdx, productCode, sellerCode, qty, unitCost, dcCost, deliveryCost, amount, payMethod, apiCode, deliveryComp, deliveryCode);
    }

    //dto >> req
    public static OiOrderRequest from(OiOrderDto dto) {
        String payMethod = "";
        if (dto.payMethod().equals(PayMethod.CARD)) {
            payMethod = "card";
        } else {
            payMethod = "trans";
        }
        return OiOrderRequest.of(
                dto.orderNo(), dto.orderIdx(), dto.status(), dto.userId(), dto.deliverIdx(), dto.productCode(), dto.sellerCode(), dto.qty(), dto.unitCost(), dto.dcCost(), dto.deliveryCost(), dto.amount(), payMethod, dto.apiCode(), dto.deliveryComp(), dto.deliveryCode()
        );
    }

    //req >> dto
    public OiOrderDto toDto () {
        PayMethod ePayMethod = null;
        if (payMethod.toLowerCase().equals("card")) {
            ePayMethod = PayMethod.CARD;
        } else {
            ePayMethod = PayMethod.TRANS;
        }
        return OiOrderDto.of(
                orderNo, orderIdx, status, userId, deliverIdx, productCode, sellerCode, qty, unitCost, dcCost, deliveryCost, amount, ePayMethod, apiCode, deliveryComp, deliveryCode
        );
    }

}
