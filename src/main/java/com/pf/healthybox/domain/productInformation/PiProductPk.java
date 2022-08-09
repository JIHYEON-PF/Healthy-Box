package com.pf.healthybox.domain.productInformation;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@RequiredArgsConstructor
@Embeddable
public class PiProductPk implements Serializable {

    @Column(length = 8)
    private String productCode;

    @Column(length = 8)
    private String sellerCode;

}
