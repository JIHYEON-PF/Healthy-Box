package com.pf.healthybox.domain.orderInformation;

import com.pf.healthybox.domain.AuditingFields;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Entity
@Table(name = "tbl_basket")
public class OiBasket extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Setter @Column(nullable = false, length = 50) private String userId;
    @Setter @Column(nullable = false, length = 8) private String productCode;
    @Setter @Column(nullable = false, length = 8) private String sellerCode;
    @Setter @Column(nullable = false) private int qty;

    public OiBasket() {}

    public OiBasket(String userId, String productCode, String sellerCode, int qty) {
        this.userId = userId;
        this.productCode = productCode;
        this.sellerCode = sellerCode;
        this.qty = qty;
    }

    public static OiBasket of(String userId, String productCode, String sellerCode, int qty) {
        return new OiBasket(userId, productCode, sellerCode, qty);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OiBasket oiBasket = (OiBasket) o;
        return idx.equals(oiBasket.idx) && userId.equals(oiBasket.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idx, userId);
    }
}
