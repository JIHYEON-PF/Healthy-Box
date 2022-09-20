package com.pf.healthybox.repository.querydsl;

import com.querydsl.core.Tuple;

import java.util.List;

public interface OiOrderRepositoryCustom {

    List<Tuple> findOrderList(String userId);

    List<Tuple> findOrderDetail(String userId, String orderNo);

    void deleteOrderDetail(String userId, String orderNo);

    String findSellerCodeByOrderNo(String orderNo);

    void updateStatusCode(String orderNo, String sellerCode, String statusCode);
}
