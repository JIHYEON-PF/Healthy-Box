package com.pf.healthybox.domain.baseInformation;


import com.pf.healthybox.domain.config.AuditingFields;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Getter
@Entity
@Table(name = "tbl_user")
public class BiUser extends AuditingFields {

    @Id
    @Column(length = 50)
    private String userId;

    @Setter @Column(nullable = false, length = 50) private String userPw;
    @Setter @Column(nullable = false, length = 3) private String authDiv;
    @Setter @Column(nullable = false, length = 3) private String authLevel;
    @Setter @Column(nullable = false, length = 30) private String userName;
    @Setter @Column(length = 30) private String nickname;
    @Setter @Column(length = 30) private String compName;
    @Setter @Column(length = 5) private String zipcode;
    @Setter @Column(length = 100) private String address1;
    @Setter @Column(length = 100) private String address2;
    @Setter @Column(nullable = false, length = 14) private String serialCode;
    @Setter @Column(nullable = false, length = 13) private String phoneNumber;
    @Setter @Column(length = 8) private String recoCode;
    @Setter @Column(length = 1) private String isDel;

    public BiUser() {}

    public BiUser(String userId, String userPw, String authDiv, String authLevel, String userName, String nickname, String compName, String zipcode, String address1, String address2, String serialCode, String phoneNumber, String recoCode, String isDel) {
        this.userId = userId;
        this.userPw = userPw;
        this.authDiv = authDiv;
        this.authLevel = authLevel;
        this.userName = userName;
        this.nickname = nickname;
        this.compName = compName;
        this.zipcode = zipcode;
        this.address1 = address1;
        this.address2 = address2;
        this.serialCode = serialCode;
        this.phoneNumber = phoneNumber;
        this.recoCode = recoCode;
        this.isDel = isDel;
    }

    public static BiUser of(String userId, String userPw, String authDiv, String authLevel, String userName, String nickname, String compName, String zipcode, String address1, String address2, String serialCode, String phoneNumber, String recoCode, String isDel) {
        return new BiUser(userId, userPw, authDiv, authLevel, userName, nickname, compName, zipcode, address1, address2, serialCode, phoneNumber, recoCode, isDel);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BiUser biUser = (BiUser) o;
        return userId.equals(biUser.userId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId);
    }


}
