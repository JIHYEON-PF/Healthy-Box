package com.pf.healthybox.dto.request.orderInformationReq;

import com.pf.healthybox.domain.orderInformation.OiOrder;
import com.pf.healthybox.dto.orderInformationDto.OiOrderCancelDataDto;

import java.math.BigDecimal;

public record OiOrderCancelDataRequest(
        String merchantUid,
        String reason,
        BigDecimal amount,
        String refundHolder,
        String refundBank,
        String refundAccount
) {

    public static OiOrderCancelDataRequest of(String merchantUid, String reason, BigDecimal amount, String refundHolder, String refundBank, String refundAccount) {
        return new OiOrderCancelDataRequest(merchantUid, reason, amount, refundHolder, refundBank, refundAccount);
    }

    // dto >> req
    public static OiOrderCancelDataRequest from(OiOrderCancelDataDto dto) {
        return OiOrderCancelDataRequest.of(
                dto.merchantUid(),
                dto.reason(),
                dto.amount(),
                dto.refundHolder(),
                dto.refundBank(),
                dto.refundAccount()
        );
    }

    // req >> dto
    public OiOrderCancelDataDto toDto() {
        return OiOrderCancelDataDto.of(
                merchantUid, reason, amount, refundHolder, refundBank, refundAccount
        );
    }

}
