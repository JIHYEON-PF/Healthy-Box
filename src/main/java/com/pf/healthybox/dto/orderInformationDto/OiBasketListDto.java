package com.pf.healthybox.dto.orderInformationDto;

public record OiBasketListDto(
        String sellerCode,
        String productCode,
        String productName,
        int qty,
        int unitCost,
        int amount
) {

    public static OiBasketListDto of(String sellerCode, String productCode, String productName, int qty, int unitCost, int amount) {
        return new OiBasketListDto(sellerCode, productCode, productName, qty, unitCost, amount);
    }
}
