package com.pf.healthybox.domain.config;

import lombok.Getter;

public enum DeliveryComp {

    EPOST("우체국택배"),
    HANJIN("한진택배"),
    LOGEN("로젠택배"),
    CJ("CJ택배");

    @Getter
    private final String description;

    DeliveryComp(String description) {
        this.description = description;
    }


    }
