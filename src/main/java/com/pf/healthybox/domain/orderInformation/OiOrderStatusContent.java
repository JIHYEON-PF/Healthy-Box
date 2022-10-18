package com.pf.healthybox.domain.orderInformation;

import com.pf.healthybox.domain.AuditingFields;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;

@Getter
@ToString(callSuper = true)
@Entity
@Table(name = "tbl_orderStatusContent")
public class OiOrderStatusContent extends AuditingFields {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Setter @Column(nullable = false, length = 20) private String orderNo;
    @Setter @Column(nullable = false, length = 50) private String userId;
    @Setter @Column(nullable = false, length = 8) private String sellerCode;
    @Setter @Column(nullable = false, length = 8) private String division;
    @Setter @Column(nullable = false, length = 100) private String title;
    @Setter @Column(nullable = false, length = 2000) private String content;

    public OiOrderStatusContent() {
    }

    public OiOrderStatusContent(String orderNo, String userId, String sellerCode, String division, String title, String content) {
        this.orderNo = orderNo;
        this.userId = userId;
        this.sellerCode = sellerCode;
        this.division = division;
        this.title = title;
        this.content = content;
    }

    public static OiOrderStatusContent of(String orderNo, String userId, String sellerCode, String division, String title, String content) {
        return new OiOrderStatusContent(orderNo, userId, sellerCode, division, title, content);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OiOrderStatusContent that = (OiOrderStatusContent) o;
        return orderNo.equals(that.orderNo) && userId.equals(that.userId) && sellerCode.equals(that.sellerCode) && division.equals(that.division) && title.equals(that.title) && content.equals(that.content);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderNo, sellerCode, division, title, content);
    }
}
