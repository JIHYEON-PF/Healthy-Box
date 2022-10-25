package com.pf.healthybox.dto.productInformationDto;

public record PiSubscribeBasketDetailProductDto(
        String productCode,
        String productName,
        int price
) {

    public static PiSubscribeBasketDetailProductDto of(String productCode, String productName, int price) {
        return new PiSubscribeBasketDetailProductDto(productCode, productName, price);
    }
}
