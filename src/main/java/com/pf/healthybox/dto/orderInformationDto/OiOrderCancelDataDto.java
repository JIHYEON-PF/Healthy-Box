package com.pf.healthybox.dto.orderInformationDto;

public record OiOrderCancelDataDto (
        String merchantUid,
        String reason,
        String refundHolder,
        String refundBank,
        String refundAccount
) {

    public static OiOrderCancelDataDto of(String merchantUid, String reason, String RefundHolder, String RefundBank, String RefundAccount) {
        return new OiOrderCancelDataDto(merchantUid, reason, RefundHolder, RefundBank, RefundAccount);
    }

}
