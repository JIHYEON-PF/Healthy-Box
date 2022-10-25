package com.pf.healthybox.dto.response.productInformationRes;

import com.pf.healthybox.dto.productInformationDto.PiSubscribeBasketDetailProductDto;

public record PiSubscribeBasketDetailProductResponse(
        String productCode,
        String productName,
        int price
) {

    public static PiSubscribeBasketDetailProductResponse of(String productCode, String productName, int price) {
        return new PiSubscribeBasketDetailProductResponse(productCode, productName, price);
    }

    // res >> dto
    public PiSubscribeBasketDetailProductDto toDto() {
        return PiSubscribeBasketDetailProductDto.of(productCode, productName, price);
    }

    // dto >> res
    public static PiSubscribeBasketDetailProductResponse from(PiSubscribeBasketDetailProductDto dto) {
        return PiSubscribeBasketDetailProductResponse.of(dto.productCode(), dto.productName(), dto.price());
    }
}
