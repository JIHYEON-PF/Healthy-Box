package com.pf.healthybox.domain.baseInformation;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
public class BiAuthPk implements Serializable { //사용자 권한 Primary Key를 관리하는 클래스

    @Column(length = 3)
    private String division;

    @Column(length = 3)
    private String level;

}
