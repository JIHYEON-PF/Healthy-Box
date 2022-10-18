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

    @Setter @Column(nullable = false, length = 3) private String product_category;
    @Setter @Column(nullable = false, length = 3) private String product_group;
    @Setter @Column(nullable = false, length = 3) private String subscribe_code;
    @Setter @Column(nullable = false, length = 200) private String subscribe_name;
    @Setter @Column(nullable = false, length = 8) private String product_code;

    public PiSubscribeProducts() {
    }

    public PiSubscribeProducts(String product_category, String product_group, String subscribe_code, String subscribe_name, String product_code) {
        this.product_category = product_category;
        this.product_group = product_group;
        this.subscribe_code = subscribe_code;
        this.subscribe_name = subscribe_name;
        this.product_code = product_code;
    }

    public PiSubscribeProducts of(String product_category, String product_group, String subscribe_code, String subscribe_name, String product_code) {
        return new PiSubscribeProducts(product_category, product_group, subscribe_code, subscribe_name, product_code);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PiSubscribeProducts that = (PiSubscribeProducts) o;
        return product_category.equals(that.product_category) && product_group.equals(that.product_group) && subscribe_code.equals(that.subscribe_code) && subscribe_name.equals(that.subscribe_name) && product_code.equals(that.product_code);
    }

    @Override
    public int hashCode() {
        return Objects.hash(product_category, product_group, subscribe_code, subscribe_name, product_code);
    }
}
