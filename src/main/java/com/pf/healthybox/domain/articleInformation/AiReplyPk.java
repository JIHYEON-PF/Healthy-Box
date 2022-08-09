package com.pf.healthybox.domain.articleInformation;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import java.io.Serializable;

@Getter
@RequiredArgsConstructor
@Embeddable
public class AiReplyPk implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column
    private Long boardIdx;
    @Column(length = 3)
    private String boardGroup;
    @Column(length = 3)
    private String boardCategory;

}
