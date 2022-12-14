package com.pf.healthybox.dto.response.productInformationRes;

import com.pf.healthybox.dto.productInformationDto.PiDivProductDto;

import java.util.List;

public record PiDivProductResponse(
        String groupName,
        List<String> categoryName
) {
    public static PiDivProductResponse of(String groupName, List<String> categoryName) {
        return new PiDivProductResponse(groupName, categoryName);
    }

    public PiDivProductResponse from(PiDivProductDto dto) {
        return new PiDivProductResponse(dto.groupName(), List.of(dto.categoryName()));
    }



}
