package com.pf.healthybox.domain.orderInformation;

import com.pf.healthybox.domain.config.DeliveryFlag;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Entity
@ToString
@Table(name = "tbl_deliver")
public class OiDeliver {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Setter @Column(nullable = false, length = 50) private String userId;
    @Setter @Column(nullable = false, length = 20) private String deliveryName;
    @Setter @Column(nullable = false, length = 5) private String zipcode;
    @Setter @Column(nullable = false, length = 100) private String address1;
    @Setter @Column(nullable = false, length = 100) private String address2;
    @Setter @Column(nullable = false, length = 3) private DeliveryFlag deliveryFlag;

    public OiDeliver() {
    }

    public OiDeliver(String userId, String deliveryName, String zipcode, String address1, String address2, DeliveryFlag deliveryFlag) {
        this.userId = userId;
        this.deliveryName = deliveryName;
        this.zipcode = zipcode;
        this.address1 = address1;
        this.address2 = address2;
        this.deliveryFlag = deliveryFlag;
    }

    public static OiDeliver of(String userId, String deliveryName, String zipcode, String address1, String address2, DeliveryFlag deliveryFlag) {
        return new OiDeliver(userId, deliveryName, zipcode, address1, address2, deliveryFlag);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OiDeliver oiDeliver = (OiDeliver) o;
        return idx.equals(oiDeliver.idx);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idx);
    }
}
