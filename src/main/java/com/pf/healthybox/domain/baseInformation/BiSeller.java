package com.pf.healthybox.domain.baseInformation;

import com.pf.healthybox.domain.AuditingFields;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Getter
@Entity
@Table(name = "tbl_seller")
public class BiSeller extends AuditingFields {

    @Id
    @Column(length = 8) private String sellerCode;

    @Setter @Column(nullable = false, length = 30) private String sellerName;
    @Setter @Column(nullable = false, unique = true, length = 14) private String serialCode;
    @Setter @Column(nullable = false, length = 5) private String zipcode;
    @Setter @Column(nullable = false, length = 100) private String address1;
    @Setter @Column(nullable = false, length = 100) private String address2;
    @Setter @Column(nullable = false, length = 13) private String phoneNumber;

    public BiSeller() {}

    public BiSeller(String sellerCode, String sellerName, String serialCode, String zipcode, String address1, String address2, String phoneNumber) {
        this.sellerCode = sellerCode;
        this.sellerName = sellerName;
        this.serialCode = serialCode;
        this.zipcode = zipcode;
        this.address1 = address1;
        this.address2 = address2;
        this.phoneNumber = phoneNumber;
    }

    public static BiSeller of(String sellerCode, String sellerName, String serialCode, String zipcode, String address1, String address2, String phoneNumber) {
        return new BiSeller(sellerCode, sellerName, serialCode, zipcode, address1, address2, phoneNumber);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BiSeller biSeller = (BiSeller) o;
        return sellerCode.equals(biSeller.sellerCode);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sellerCode);
    }
}
