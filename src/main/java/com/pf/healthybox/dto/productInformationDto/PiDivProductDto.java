package com.pf.healthybox.dto.productInformationDto;

import com.pf.healthybox.domain.productInformation.PiDivProduct;
import com.pf.healthybox.domain.productInformation.PiDivProductPk;

public record PiDivProductDto(
        PiDivProductPkDto piDivProductPkDto,
        String groupName,
        String categoryName,
        String isUsed
) {

    public static PiDivProductDto of(PiDivProductPkDto piDivProductPkDto, String groupName, String categoryName, String isUsed) {
        return new PiDivProductDto(piDivProductPkDto, groupName, categoryName, isUsed);
    }

    // entity > dto
    public static PiDivProductDto from(PiDivProduct entity) {
        return new PiDivProductDto(
                PiDivProductPkDto.from(entity.getPiDivProductPk()),
                entity.getGroupName(),
                entity.getCategoryName(),
                entity.getIsUsed()
                );
    }

    // dto >> entity
    public PiDivProduct toEntity(PiDivProductPk piDivProductPk) {
        return PiDivProduct.of(
                piDivProductPk,
                groupName,
                categoryName,
                isUsed
        );
    }
}
