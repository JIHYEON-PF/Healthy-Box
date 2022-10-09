package com.pf.healthybox.domain.config;

import lombok.Getter;

public enum PayMethod {

    CARD("카드결제"),
    TRANS("현금결제");

    @Getter private final String description;

    PayMethod(String description) {
        this.description = description;
    }

}
