package com.pf.healthybox.repository.querydsl;

import com.pf.healthybox.domain.orderInformation.OiSubscribeBasket;
import com.querydsl.core.Tuple;

import java.util.List;

public interface OiBasketRepositoryCustom {

    List<Tuple> showBasketList(String userId);

    List<Tuple> showBasketPrice(String userId);

    void updateBasketQty(String userId, String productCode, Integer qty);

    void deleteBasketItems(String userId, String[] productCode);

    List<Tuple> showSubscribeBasketList(String userId);

    List<Tuple> findByUserIdAndBasketNo(String userId, String basketNo);

    List<Tuple> findSubscribeDetailProducts(String userId, String basketNo);

    String findSubscribeCodeByUserIdAndBasketNo(String userId, String basketNo);

    OiSubscribeBasket findByUserIdAndBasketNoAndProductIdx(String userId, String basketNo, int productIdx);

    void deleteSubscribeBasketItems(String userId, String basketNo);
}
