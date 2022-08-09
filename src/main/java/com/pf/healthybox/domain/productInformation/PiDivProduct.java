package com.pf.healthybox.domain.productInformation;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Entity
@Table(name = "tbl_div_product")
public class PiDivProduct {

    @EmbeddedId
    private PiDivProductPk piDivProductPk;

    @Setter @Column(nullable = false, length = 50) private String groupName;
    @Setter @Column(nullable = false, length = 50) private String categoryName;
    @Setter @Column(nullable = false, length = 1) private String isUsed;

    public PiDivProduct() {}

    public PiDivProduct(PiDivProductPk piDivProductPk, String groupName, String categoryName, String isUsed) {
        this.piDivProductPk = piDivProductPk;
        this.groupName = groupName;
        this.categoryName = categoryName;
        this.isUsed = isUsed;
    }

    public static PiDivProduct of(PiDivProductPk piDivProductPk, String groupName, String categoryName, String isUsed) {
        return new PiDivProduct(piDivProductPk, groupName, categoryName, isUsed);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PiDivProduct that = (PiDivProduct) o;
        return piDivProductPk.equals(that.piDivProductPk);
    }

    @Override
    public int hashCode() {
        return Objects.hash(piDivProductPk);
    }
}
