package com.pf.healthybox.domain.baseInformation;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Getter
@Entity
@Table(name = "tbl_auth")
public class BiAuth { // 사용자 권한 Entity

    @EmbeddedId
    private BiAuthPk biAuthPk;

    @Setter @Column(nullable = false, length = 30) private String authName;
    @Setter @Column(nullable = false, length = 1) private String registerProd;
    @Setter @Column(nullable = false, length = 1) private String updatedProd;
    @Setter @Column(nullable = false, length = 1) private String deleteProd;
    @Setter @Column(nullable = false, length = 1) private String manageUser;
    @Setter @Column(nullable = false, length = 1) private String manageOrder;
    @Setter @Column(nullable = false, length = 1) private String manageBoard;

    public BiAuth() {}

    public BiAuth(BiAuthPk biAuthPk, String authName, String registerProd, String updatedProd, String deleteProd, String manageUser, String manageOrder, String manageBoard) {
        this.biAuthPk = biAuthPk;
        this.authName = authName;
        this.registerProd = registerProd;
        this.updatedProd = updatedProd;
        this.deleteProd = deleteProd;
        this.manageUser = manageUser;
        this.manageOrder = manageOrder;
        this.manageBoard = manageBoard;
    }

    public static BiAuth of(BiAuthPk biAuthPk, String authName, String registerProd, String updatedProd, String deleteProd, String manageUser, String manageOrder, String manageBoard) {
        return new BiAuth(biAuthPk, authName, registerProd, updatedProd, deleteProd, manageUser, manageOrder, manageBoard);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BiAuth biAuth = (BiAuth) o;
        return biAuthPk.equals(biAuth.biAuthPk);
    }

    @Override
    public int hashCode() {
        return Objects.hash(biAuthPk);
    }
}

