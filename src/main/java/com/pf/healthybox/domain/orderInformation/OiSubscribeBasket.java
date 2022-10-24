package com.pf.healthybox.domain.orderInformation;

import com.pf.healthybox.domain.AuditingFields;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@ToString(callSuper = true)
@Entity
@Table(name = "tbl_subscribe_basket")
public class OiSubscribeBasket extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Setter @Column(nullable = false, length = 16) private String basketNo; // 정기배송 장바구니 코드 (형식: SUB221020-091711)
    @Setter @Column(nullable = false, length = 8) private String userId; // 장바구니 주인 아이디
    @Setter @Column(nullable = false, length = 3) private String subscribeCode; // 정기배송 상품 코드
    @Setter @Column(nullable = false, length = 8) private String productCode; // 아이템 코드
    @Setter @Column(nullable = false, length = 8) private String sellerCode; // 아이템의 판매자 코드
    @Setter @Column(nullable = false) private int productIdx; // 아이템 인덱스(중복되는 아이템의 구분을 위한 컬럼)
    @Setter @Column(nullable = false) private int qty; //상품의 수량
    @Setter @Column(nullable = false) private LocalDateTime deliveryDate; // 상품의 배송 예정일

    public OiSubscribeBasket() {
    }

    public OiSubscribeBasket(String basketNo, String userId, String subscribeCode, String productCode, String sellerCode, int productIdx, int qty, LocalDateTime deliveryDate) {
        this.basketNo = basketNo;
        this.userId = userId;
        this.subscribeCode = subscribeCode;
        this.productCode = productCode;
        this.sellerCode = sellerCode;
        this.productIdx = productIdx;
        this.qty = qty;
        this.deliveryDate = deliveryDate;
    }

    public static OiSubscribeBasket of(String basketNo, String userId, String subscribeCode, String productCode, String sellerCode, int productIdx, int qty, LocalDateTime deliveryDate) {
        return new OiSubscribeBasket(basketNo, userId, subscribeCode, productCode, sellerCode, productIdx, qty, deliveryDate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OiSubscribeBasket that = (OiSubscribeBasket) o;
        return basketNo.equals(that.basketNo) && productIdx == that.productIdx && qty == that.qty && userId.equals(that.userId) && subscribeCode.equals(that.subscribeCode) && productCode.equals(that.productCode) && sellerCode.equals(that.sellerCode) && deliveryDate.equals(that.deliveryDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, subscribeCode, productCode, sellerCode, productIdx, qty, deliveryDate);
    }
}
