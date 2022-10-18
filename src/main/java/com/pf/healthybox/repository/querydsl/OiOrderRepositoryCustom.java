package com.pf.healthybox.repository.querydsl;

import com.pf.healthybox.dto.orderInformationDto.OiOrderDetailDto;
import com.querydsl.core.Tuple;

import java.util.List;

public interface OiOrderRepositoryCustom {

    List<Tuple> findOrderList(String userId, String isSubscribe);

    List<Tuple> findOrderDetail(String userId, String orderNo, String isSubscribe);

    void deleteOrderDetail(String userId, String orderNo);

    String findSellerCodeByOrderNo(String orderNo);

    void updateStatusCode(String orderNo, String sellerCode, String statusCode);

    List<Tuple> findSubscribeOrderList(String userId, String isSubscribe);

}
