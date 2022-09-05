package com.pf.healthybox.domain.config;

import lombok.Getter;

public enum DeliveryFlag {

    DEF("기본 배송 정보"),
    REG("저장 배송 정보");

    @Getter private final String description;

    DeliveryFlag(String description) {
        this.description = description;
    }
}
