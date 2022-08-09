package com.pf.healthybox.repository.querydsl;

import com.querydsl.core.Tuple;

import java.util.List;

public interface PiDivProductRepositoryCustom {

    List<String> findAllDistinctProductGroup();

    List<Tuple> findAllProductGroup();

}
