package com.pf.healthybox.domain.productInformation;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Entity
@Table(name = "tbl_subscribe_products")
public class PiSubscribeProducts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Setter @Column(nullable = false, length = 3) private String productCategory;
    @Setter @Column(nullable = false, length = 3) private String productGroup;
    @Setter @Column(nullable = false, length = 3) private String subscribeCode;
    @Setter @Column(nullable = false, length = 200) private String subscribeName;
    @Setter @Column(nullable = false, length = 8) private String sellerCode;
    @Setter @Column(nullable = false, length = 8) private String productCode;

    public PiSubscribeProducts() {
    }

    public PiSubscribeProducts(String productCategory, String productGroup, String subscribeCode, String subscribeName, String sellerCode, String productCode) {
        this.productCategory = productCategory;
        this.productGroup = productGroup;
        this.subscribeCode = subscribeCode;
        this.subscribeName = subscribeName;
        this.sellerCode = sellerCode;
        this.productCode = productCode;
    }

    public PiSubscribeProducts of(String productCategory, String productGroup, String subscribeCode, String subscribeName, String sellerCode, String productCode) {
        return new PiSubscribeProducts(productCategory, productGroup, subscribeCode, subscribeName, sellerCode, productCode);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PiSubscribeProducts that = (PiSubscribeProducts) o;
        return idx.equals(that.idx) && productCategory.equals(that.productCategory) && productGroup.equals(that.productGroup) && subscribeCode.equals(that.subscribeCode) && subscribeName.equals(that.subscribeName) && sellerCode.equals(that.sellerCode) && productCode.equals(that.productCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idx, productCategory, productGroup, subscribeCode, subscribeName, sellerCode, productCode);
    }
}
