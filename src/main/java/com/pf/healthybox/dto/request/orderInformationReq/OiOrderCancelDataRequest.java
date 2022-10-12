package com.pf.healthybox.dto.request.orderInformationReq;

import com.pf.healthybox.domain.orderInformation.OiOrder;
import com.pf.healthybox.dto.orderInformationDto.OiOrderCancelDataDto;

public record OiOrderCancelDataRequest(
        String merchantUid,
        String reason,
        String refundHolder,
        String refundBank,
        String refundAccount
) {

    public static OiOrderCancelDataRequest of(String merchantUid, String reason, String RefundHolder, String RefundBank, String RefundAccount) {
        return new OiOrderCancelDataRequest(merchantUid, reason, RefundHolder, RefundBank, RefundAccount);
    }

    // dto >> req
    public static OiOrderCancelDataRequest from(OiOrderCancelDataDto dto) {
        return OiOrderCancelDataRequest.of(
                dto.merchantUid(),
                dto.reason(),
                dto.refundHolder(),
                dto.refundBank(),
                dto.refundAccount()
        );
    }

    // req >> dto
    public OiOrderCancelDataDto toDto() {
        return OiOrderCancelDataDto.of(
                merchantUid, reason, refundHolder, refundBank, refundAccount
        );
    }

}
