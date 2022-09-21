package com.pf.healthybox.dto.response.orderInformationRes;

import com.pf.healthybox.dto.orderInformationDto.OiBasketListDto;

public record OiBasketListResponse(
        String sellerCode,
        String productCode,
        String productName,
        int qty,
        int unitCost,
        int amount
) {

    public static OiBasketListResponse of(String sellerCode, String productCode, String productName, int qty, int unitCost, int amount) {
        return new OiBasketListResponse(sellerCode, productCode, productName, qty, unitCost, amount);
    }

    // res to dto
    public OiBasketListDto toDto() {
        return OiBasketListDto.of(sellerCode, productCode, productName, qty, unitCost, amount);
    }

    // res from dto
    public static OiBasketListResponse from(OiBasketListDto dto) {
        return OiBasketListResponse.of(dto.sellerCode(), dto.productCode(), dto.productName(), dto.qty(), dto.unitCost(), dto.amount());
    }
}
