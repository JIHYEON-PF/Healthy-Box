package com.pf.healthybox.domain.config;

import lombok.Getter;

public enum BoardCategory {

    NT("공지사항"),
    EV("이벤트"),
    TOA("홈페이지 문의"),
    TOS("판매자 문의"),
    FAQ("FAQ"),
    RE("후기");

    @Getter private final String description;

    BoardCategory(String description) {
        this.description = description;
    }

}
