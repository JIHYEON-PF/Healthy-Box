package com.pf.healthybox.domain.productInformation;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Embeddable
public class PiDivProductPk implements Serializable {

    @Column(length = 3)
    private String productGroup;

    @Column(length = 3)
    private String productCategory;

    public PiDivProductPk() {
    }

    public PiDivProductPk(String productGroup, String productCategory) {
        this.productGroup = productGroup;
        this.productCategory = productCategory;
    }

    public static PiDivProductPk of(String productGroup, String productCategory) {
        return new PiDivProductPk(productGroup, productCategory);
    }

}
