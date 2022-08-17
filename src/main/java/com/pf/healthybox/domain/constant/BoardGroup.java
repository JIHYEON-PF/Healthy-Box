package com.pf.healthybox.domain.constant;

import lombok.Getter;

public enum BoardGroup {

    NT("공지사항"),
    EV("이벤트"),
    QA("문의"),
    RE("후기");

    @Getter private final String description;

    BoardGroup(String description) {
        this.description = description;
    }

}
