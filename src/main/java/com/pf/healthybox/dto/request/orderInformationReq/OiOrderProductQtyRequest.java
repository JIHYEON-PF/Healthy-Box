package com.pf.healthybox.dto.request.orderInformationReq;

import com.pf.healthybox.dto.orderInformationDto.OiOrderProductQtyDto;

public record OiOrderProductQtyRequest(
        String productCode,
        String sellerCode,
        String productName,
        Integer unitCost,
        Integer qty,
        Integer dcCost,
        Integer deliveryCost
) {

    public static OiOrderProductQtyRequest of(String productCode, String sellerCode, String productName, Integer unitCost, Integer qty, Integer dcCost, Integer deliveryCost) {
        return new OiOrderProductQtyRequest(productCode, sellerCode, productName, unitCost, qty, dcCost, deliveryCost);
    }

    //dto >> req
    public static OiOrderProductQtyRequest from(OiOrderProductQtyDto dto) {
        return OiOrderProductQtyRequest.of(
                dto.productCode(), dto.sellerCode(), dto.productName(), dto.unitCost(), dto.qty(), dto.dcCost(), dto.deliveryCost()
        );
    }

    //req >> dto
    public OiOrderProductQtyDto toDto() {
        return OiOrderProductQtyDto.of(
                productCode, sellerCode, productName, unitCost, qty, dcCost, deliveryCost
        );
    }

}
