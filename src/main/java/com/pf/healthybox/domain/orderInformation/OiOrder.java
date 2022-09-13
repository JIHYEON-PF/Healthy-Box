package com.pf.healthybox.domain.orderInformation;

import com.pf.healthybox.domain.AuditingFields;
import com.pf.healthybox.domain.config.PayMethod;
import com.pf.healthybox.domain.config.Status;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Entity
@Table(name = "tbl_order")
public class OiOrder extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Setter @Column(nullable = false, length = 10) private String orderNo;
    @Setter @Column(nullable = false) private int orderIdx;
    @Setter @Column(nullable = false, length = 20) private Status status;
    @Setter @Column(nullable = false, length = 50) private String userId;
    @Setter @Column(nullable = false) private Long deliverIdx;
    @Setter @Column(nullable = false, length = 8) private String productCode;
    @Setter @Column(nullable = false, length = 8) private String sellerCode;
    @Setter @Column(nullable = false) private int qty;
    @Setter @Column(nullable = false) private int unitCost;
    @Setter @Column(nullable = false) private int amount;
    @Setter @Column(nullable = false, length = 4) private PayMethod payMethod;
    @Setter @Column(length = 30) private String apiCode;

    public OiOrder() {}

    public OiOrder(String orderNo, int orderIdx, Status status, String userId, Long deliverIdx, String productCode, String sellerCode, int qty, int unitCost, int amount, PayMethod payMethod, String apiCode) {
        this.orderNo = orderNo;
        this.orderIdx = orderIdx;
        this.status = status;
        this.userId = userId;
        this.deliverIdx = deliverIdx;
        this.productCode = productCode;
        this.sellerCode = sellerCode;
        this.qty = qty;
        this.unitCost = unitCost;
        this.amount = amount;
        this.payMethod = payMethod;
        this.apiCode = apiCode;
    }

    public static OiOrder of(String orderNo, int orderIdx, Status status, String userId, Long deliverIdx, String productCode, String sellerCode, int qty, int unitCost, int amount, PayMethod payMethod, String apiCode) {
        return new OiOrder(orderNo, orderIdx, status, userId, deliverIdx, productCode, sellerCode, qty, unitCost, amount, payMethod, apiCode);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OiOrder oiOrder = (OiOrder) o;
        return idx.equals(oiOrder.idx);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idx);
    }
}
