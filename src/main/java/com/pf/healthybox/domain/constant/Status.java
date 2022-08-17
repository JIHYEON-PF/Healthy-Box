package com.pf.healthybox.domain.constant;

import lombok.Getter;

public enum Status {

    ORDERED("주문"),
    BEFOREDELIVERY("출고"),
    DELIVERING("배송중"),
    AFTERDELIVERY("배송완료"),
    RETURN("반품"),
    EXCHANGE("교환"),
    CANCEL("취소");

    @Getter
    private final String description;

    Status(String description) {
        this.description = description;
    }

}
