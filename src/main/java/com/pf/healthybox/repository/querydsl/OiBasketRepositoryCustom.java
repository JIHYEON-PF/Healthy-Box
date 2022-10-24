package com.pf.healthybox.repository.querydsl;

import com.pf.healthybox.domain.orderInformation.OiBasket;
import com.querydsl.core.Tuple;

import java.util.List;

public interface OiBasketRepositoryCustom {

    List<Tuple> showBasketList(String userId);

    List<Tuple> showBasketPrice(String userId);

    void updateBasketQty(String userId, String productCode, Integer qty);

    void deleteBasketItems(String userId, String[] productCode);

    List<Tuple> showSubscribeBasketList(String userId);
}
