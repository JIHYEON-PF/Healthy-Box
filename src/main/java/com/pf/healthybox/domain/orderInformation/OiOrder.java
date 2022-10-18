package com.pf.healthybox.domain.orderInformation;

import com.pf.healthybox.domain.AuditingFields;
import com.pf.healthybox.domain.config.DeliveryComp;
import com.pf.healthybox.domain.config.PayMethod;
import com.pf.healthybox.domain.config.Status;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Entity
@Table(name = "tbl_order")
public class OiOrder extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx; // 주문번호 인덱스

    @Setter @Column(nullable = false, length = 20) private String orderNo; // 주문번호 형식 : ORDyyMMdd-hhmmss
    @Setter @Column(nullable = false) private int orderIdx; // 하나의 주문번호의 일렬번호
    @Setter @Column(nullable = false, length = 20) private Status status; // 주문 상태
    @Setter @Column(nullable = false, length = 50) private String userId; // 주문한 사람의 ID
    @Setter @Column(nullable = false) private Long deliverIdx; // 주문 배송 정보 일렬번호
    @Setter @Column(nullable = false, length = 8) private String productCode; // 주문 품목코드
    @Setter @Column(nullable = false, length = 8) private String sellerCode; // 주문 품목의 판매자 코드
    @Setter @Column(nullable = false) private int qty; // 주문 수량
    @Setter @Column(nullable = false) private int unitCost; // 주문 품목의단가
    @Setter @Column private int dcCost; // 주문 품목의 할인가
    @Setter @Column private int deliveryCost; // 주문 배송비
    @Setter @Column(nullable = false) private int amount; // 주문 총 금액
    @Setter @Column(nullable = false, length = 4) private PayMethod payMethod; // 주문 결제 방법
    @Setter @Column(length = 16) private String apiCode; // 주문 결제 API 코드(아임포트)
    @Setter @Column(nullable = true, length=10) private String deliveryComp; // 택배화사 코드
    @Setter @Column(length=50) private String deliveryCode; // 송장번호
    @Setter @Column(length=15) private String cardName;// 카드 명칭
    @Setter @Column(length=16) private String cardNum;// 마스킹 카드 번호
    @Setter @Column private int quota;// 할부 개월 수
    @Setter @Column private String isSubscribe; // 정기배송 주문 및 단품 주문 플래그(정기배송 : Y / 단품주문 : N)
    @Setter @Column(length = 3) private String subscribeCode; // 정기배송 네임 테이블과 연결되는 참조값
    @Setter @Column LocalDateTime deliveryDate; // 상품의 배송예정일

    public OiOrder() {}

    public OiOrder(String orderNo, int orderIdx, Status status, String userId, Long deliverIdx, String productCode, String sellerCode, int qty, int unitCost, int dcCost, int deliveryCost, int amount, PayMethod payMethod, String apiCode, String deliveryComp, String deliveryCode, String cardName, String cardNum, int quota, String isSubscribe, String subscribeCode, LocalDateTime deliveryDate) {
        this.orderNo = orderNo;
        this.orderIdx = orderIdx;
        this.status = status;
        this.userId = userId;
        this.deliverIdx = deliverIdx;
        this.productCode = productCode;
        this.sellerCode = sellerCode;
        this.qty = qty;
        this.unitCost = unitCost;
        this.dcCost = dcCost;
        this.deliveryCost = deliveryCost;
        this.amount = amount;
        this.payMethod = payMethod;
        this.apiCode = apiCode;
        this.deliveryComp = deliveryComp;
        this.deliveryCode = deliveryCode;
        this.cardName = cardName;
        this.cardNum = cardNum;
        this.quota = quota;
        this.isSubscribe = isSubscribe;
        this.subscribeCode = subscribeCode;
        this.deliveryDate = deliveryDate;
    }

    public static OiOrder of(String orderNo, int orderIdx, Status status, String userId, Long deliverIdx, String productCode, String sellerCode, int qty, int unitCost, int dcCost, int deliveryCost, int amount, PayMethod payMethod, String apiCode, String deliveryComp, String deliveryCode, String cardName, String cardNum, int quota, String isSubscribe, String subscribeCode, LocalDateTime deliveryDate) {
        return new OiOrder(orderNo, orderIdx, status, userId, deliverIdx, productCode, sellerCode, qty, unitCost, dcCost, deliveryCost, amount, payMethod, apiCode, deliveryComp, deliveryCode, cardName, cardNum, quota, isSubscribe, subscribeCode, deliveryDate);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OiOrder oiOrder = (OiOrder) o;
        return orderIdx == oiOrder.orderIdx && qty == oiOrder.qty && unitCost == oiOrder.unitCost && dcCost == oiOrder.dcCost && deliveryCost == oiOrder.deliveryCost && amount == oiOrder.amount && quota == oiOrder.quota && idx.equals(oiOrder.idx) && orderNo.equals(oiOrder.orderNo) && status == oiOrder.status && userId.equals(oiOrder.userId) && deliverIdx.equals(oiOrder.deliverIdx) && productCode.equals(oiOrder.productCode) && sellerCode.equals(oiOrder.sellerCode) && payMethod == oiOrder.payMethod && apiCode.equals(oiOrder.apiCode) && Objects.equals(deliveryComp, oiOrder.deliveryComp) && Objects.equals(deliveryCode, oiOrder.deliveryCode) && Objects.equals(cardName, oiOrder.cardName) && Objects.equals(cardNum, oiOrder.cardNum) && isSubscribe.equals(oiOrder.isSubscribe);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idx, orderNo, orderIdx, status, userId, deliverIdx, productCode, sellerCode, qty, unitCost, dcCost, deliveryCost, amount, payMethod, apiCode, deliveryComp, deliveryCode, cardName, cardNum, quota, isSubscribe);
    }
}
