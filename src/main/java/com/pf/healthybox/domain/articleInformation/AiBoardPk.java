package com.pf.healthybox.domain.articleInformation;

import com.pf.healthybox.domain.config.BoardCategory;
import com.pf.healthybox.domain.config.BoardGroup;
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
public class AiBoardPk implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idx;

    @Column(length = 3)
    private BoardGroup boardGroup;

    @Column(length = 3)
    private BoardCategory boardCategory;

}
