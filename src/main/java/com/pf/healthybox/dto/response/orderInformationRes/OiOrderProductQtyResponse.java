package com.pf.healthybox.dto.response.orderInformationRes;

import com.pf.healthybox.dto.orderInformationDto.OiOrderProductQtyDto;
import lombok.ToString;

public record OiOrderProductQtyResponse (
        String productCode,
        String sellerCode,
        String productName,
        Integer unitCost,
        Integer qty,
        Integer dcCost,
        Integer deliveryCost
) {

    public static OiOrderProductQtyResponse of(String productCode, String sellerCode, String productName, Integer unitCost, Integer qty, Integer dcCost, Integer deliveryCost) {
        return new OiOrderProductQtyResponse(productCode, sellerCode, productName, unitCost, qty, dcCost, deliveryCost);
    }

    //dto >> res
    public static OiOrderProductQtyResponse from(OiOrderProductQtyDto dto) {
        return OiOrderProductQtyResponse.of(
                dto.productCode(), dto.sellerCode(), dto.productName(), dto.unitCost(), dto.qty(), dto.dcCost(), dto.deliveryCost()
        );
    }

    //res >> dto
    public OiOrderProductQtyDto toDto() {
        return OiOrderProductQtyDto.of(
                productCode, sellerCode, productName, unitCost, qty, dcCost, deliveryCost
        );
    }

}
