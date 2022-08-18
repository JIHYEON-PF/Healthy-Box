package com.pf.healthybox.dto.productInformationDto;

import com.pf.healthybox.domain.productInformation.PiDivProductPk;

public record PiDivProductPkDto(String productGroup, String productCategory) {

    public static PiDivProductPkDto of (String productGroup, String productCategory) {
        return new PiDivProductPkDto(productGroup, productCategory);
    }

    // entity > dto
    public static PiDivProductPkDto from(PiDivProductPk entity) {
        return new PiDivProductPkDto(entity.getProductGroup(), entity.getProductGroup());
    }

    // dto > entity
    public PiDivProductPk toEntity() {
        return PiDivProductPk.of(productGroup, productCategory);
    }
}
