package com.pf.healthybox.domain.adminConfig;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "tbl_environment")
public class Environment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;
    
    @Setter @Column(nullable = false, length = 30) private String apiName;
    @Setter @Column(nullable = false, length = 100) private String apiKey;
}
