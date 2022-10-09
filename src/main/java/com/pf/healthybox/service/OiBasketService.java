package com.pf.healthybox.service;

import com.pf.healthybox.domain.orderInformation.OiBasket;
import com.pf.healthybox.dto.response.orderInformationRes.OiBasketListResponse;
import com.pf.healthybox.repository.OiBasketRepository;
import com.querydsl.core.Tuple;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class OiBasketService {

    private final OiBasketRepository oiBasketRepository;

    public OiBasketService(@Autowired OiBasketRepository oiBasketRepository) {
        this.oiBasketRepository = oiBasketRepository;
    }

    //배송내역 조회
    public List<OiBasketListResponse> returnBasketList(String userId) {
        List<Tuple> tuples = oiBasketRepository.showBasketList(userId);
        List<OiBasketListResponse> res = new ArrayList<>();

        for (Tuple tuple : tuples) {
            res.add(
                    OiBasketListResponse.of(
                            tuple.get(0, String.class),
                            tuple.get(1, String.class),
                            tuple.get(2, String.class),
                            tuple.get(3, int.class),
                            tuple.get(4, int.class),
                            tuple.get(5, int.class)
                    )
            );
        }
        return res;
    }

    public List<Integer> returnPriceInformation(String userId) {
        List<Tuple> tuples = oiBasketRepository.showBasketPrice(userId);
        Integer amount = 0;
        Integer deliveryCost = 0;
        for (Tuple tuple : tuples) {
            amount += tuple.get(0, Integer.class);
            deliveryCost += (tuple.get(0, Integer.class) == 0 ? 0 :tuple.get(1, Integer.class));
        }

        return List.of(amount, deliveryCost);
    }

    @Transactional
    public void updateBasketQty(String userId, String productCode, Integer qty) {
        oiBasketRepository.updateBasketQty(userId, productCode, qty);
    }

    @Transactional
    public void deleteBasketItems(String userId, String[] productCode) {
        oiBasketRepository.deleteBasketItems(userId, productCode);
    }
}
