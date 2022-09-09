package com.pf.healthybox.repository.querydsl;

import com.pf.healthybox.domain.baseInformation.QBiPoint;
import com.querydsl.core.Tuple;
import org.springframework.data.querydsl.binding.QuerydslBindings;

import java.util.List;

public interface OiOrderRepositoryCustom {

    List<Tuple> findOrderList(String userId);

}