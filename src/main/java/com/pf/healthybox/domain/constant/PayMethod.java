package com.pf.healthybox.domain.constant;

import lombok.Getter;

public enum PayMethod {

    CARD("카드결제"),
    CASH("현금결제");

    @Getter private final String description;

    PayMethod(String description) {
        this.description = description;
    }

}
