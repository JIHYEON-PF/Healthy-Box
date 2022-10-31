package com.pf.healthybox.dto.orderInformationDto;

import java.math.BigDecimal;

public record OiOrderCancelDataDto (
        String merchantUid,
        String reason,
        BigDecimal amount,
        String refundHolder,
        String refundBank,
        String refundAccount
) {

    public static OiOrderCancelDataDto of(String merchantUid, String reason, BigDecimal amount, String refundHolder, String refundBank, String refundAccount) {
        return new OiOrderCancelDataDto(merchantUid, reason, amount, refundHolder, refundBank, refundAccount);
    }

}
