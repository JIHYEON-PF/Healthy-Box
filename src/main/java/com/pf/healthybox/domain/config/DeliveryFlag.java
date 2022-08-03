package com.pf.healthybox.domain.config;

import lombok.Getter;

public enum DeliveryFlag {

    NEW("단건 배송 정보"),
    DEF("기본 배송 정보");

    @Getter private final String description;

    DeliveryFlag(String description) {
        this.description = description;
    }
}
