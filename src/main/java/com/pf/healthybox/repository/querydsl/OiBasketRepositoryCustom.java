package com.pf.healthybox.repository.querydsl;

import com.querydsl.core.Tuple;

import java.util.List;

public interface OiBasketRepositoryCustom {

    List<Tuple> showBasketList(String userId);

    List<Tuple> showBasketPrice(String userId);

}
