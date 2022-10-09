package com.pf.healthybox.repository.querydsl;

import com.pf.healthybox.domain.productInformation.PiProduct;

import java.util.List;

public interface PiProductRepositoryCustom {

    PiProduct findProducts(String productCode, String sellerCode);

}
