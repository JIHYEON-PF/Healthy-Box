package com.pf.healthybox.dto.response.productInformationRes;

import com.pf.healthybox.dto.productInformationDto.PiDivProductPkDto;

public record PiDivProductPkResponse(String productGroup, String productCategory) {

    public static PiDivProductPkResponse of(String productGroup, String productCategory) {
        return new PiDivProductPkResponse(productGroup, productCategory);
    }

    // dto > response
    public static PiDivProductPkResponse from(PiDivProductPkDto piDivProductPkDto) {
        return new PiDivProductPkResponse(piDivProductPkDto.productGroup(), piDivProductPkDto.productCategory());
    }

}
