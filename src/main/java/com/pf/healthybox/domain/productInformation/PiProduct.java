package com.pf.healthybox.domain.productInformation;

import com.pf.healthybox.domain.AuditingFields;
import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Entity
@Table(name = "tbl_product")
public class PiProduct extends AuditingFields {

    @EmbeddedId
    private PiProductPk piProductPk;

    @Setter@Column(nullable = false, length = 50) private String productName;
    @Setter @Column(nullable = false, length = 3) private String productGroup;
    @Setter @Column(nullable = false, length = 3) private String productCategory;
    @Setter @Column(nullable = false) private int price;
    @Setter @Column(nullable = false) private int stock;
    @Setter @Column(nullable = false, columnDefinition = "MEDIUMBLOB") private String detail;
    @Setter @Column(nullable = false, length = 1) private String isUsed;

    public PiProduct() {}

    public PiProduct(PiProductPk piProductPk, String productName, String productGroup, String productCategory, int price, int stock, String detail, String isUsed) {
        this.piProductPk = piProductPk;
        this.productName = productName;
        this.productGroup = productGroup;
        this.productCategory = productCategory;
        this.price = price;
        this.stock = stock;
        this.detail = detail;
        this.isUsed = isUsed;
    }

    public static PiProduct of(PiProductPk piProductPk, String productName, String productGroup, String productCategory, int price, int stock, String detail, String isUsed) {
        return new PiProduct(piProductPk, productName, productGroup, productCategory, price, stock, detail, isUsed);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PiProduct piProduct = (PiProduct) o;
        return piProductPk.equals(piProduct.piProductPk);
    }

    @Override
    public int hashCode() {
        return Objects.hash(piProductPk);
    }
}

