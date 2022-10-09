package com.pf.healthybox.dto.orderInformationDto;

public record OiOrderProductQtyDto(
        String productCode,
        String sellerCode,
        String productName,
        Integer unitCost,
        Integer qty,
        Integer dcCost,
        Integer deliveryCost
) {

    public static OiOrderProductQtyDto of(String productCode, String sellerCode, String productName, Integer unitCost, Integer qty, Integer dcCost, Integer deliveryCost) {
        return new OiOrderProductQtyDto(
                productCode, sellerCode, productName, unitCost, qty, dcCost, deliveryCost);
    }
}
