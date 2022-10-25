package com.pf.healthybox.service;

import com.pf.healthybox.domain.orderInformation.OiSubscribeBasket;
import com.pf.healthybox.dto.orderInformationDto.OiSubscribeBasketDto;
import com.pf.healthybox.dto.response.orderInformationRes.OiBasketListResponse;
import com.pf.healthybox.dto.response.orderInformationRes.OiSubscribeBasketDetailResponse;
import com.pf.healthybox.dto.response.orderInformationRes.OiSubscribeBasketListResponse;
import com.pf.healthybox.dto.response.productInformationRes.PiSubscribeBasketDetailProductResponse;
import com.pf.healthybox.repository.OiBasketRepository;
import com.querydsl.core.Tuple;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
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

    public List<OiSubscribeBasketListResponse> returnSubscribeBasketList(String userId) {
        List<Tuple> tuples = oiBasketRepository.showSubscribeBasketList(userId);
        List<OiSubscribeBasketListResponse> res = new ArrayList<>();

        for (Tuple tuple : tuples) {
            res.add(
                    OiSubscribeBasketListResponse.of(
                            tuple.get(0, String.class),         //basketNo
                            tuple.get(1, LocalDateTime.class),  //createdAt
                            tuple.get(2, String.class),         //subscribeCode
                            tuple.get(3, String.class),         //subscribeName
                            tuple.get(4, int.class),            //amount
                            tuple.get(5, LocalDateTime.class),  //startDate
                            tuple.get(6, LocalDateTime.class),  //endDate
                            tuple.get(7, String.class)          //sellerCode
                    )
            );
        }

        return res;
    }

    public List<OiSubscribeBasketDetailResponse> returnSubscribeBasketDetail(String userId, String basketNo) {
         List<Tuple> tuples = oiBasketRepository.findByUserIdAndBasketNo(userId, basketNo);
         List<OiSubscribeBasketDetailResponse> res = new ArrayList<>();
         for (Tuple tuple : tuples) {
             res.add(
                     OiSubscribeBasketDetailResponse.of(
                             tuple.get(0, String.class),        //basketNo
                             tuple.get(1, LocalDateTime.class), //deliveryDate
                             tuple.get(2, String.class),        //productCode
                             tuple.get(3, String.class),        //productName
                             tuple.get(4, int.class),           //productIdx
                             tuple.get(5, int.class),           //qty
                             tuple.get(6, String.class),        //sellerCode
                             tuple.get(7, String.class),        //subscribeCode
                             tuple.get(8, String.class),        //subscribeName
                             tuple.get(9, int.class),           //price
                             tuple.get(10, int.class),           //amount
                             tuple.get(11, LocalDateTime.class),//startDate
                             tuple.get(12, LocalDateTime.class) //endDate
                     )
             );
         }
        return res;
    }

    public List<PiSubscribeBasketDetailProductResponse> returnSubscribeProducts(String userId, String basketNo) {
        List<Tuple> tuples = oiBasketRepository.findSubscribeDetailProducts(userId, basketNo);
        List<PiSubscribeBasketDetailProductResponse> res = new ArrayList<>();

        for (Tuple tuple : tuples) {
            res.add(
                    PiSubscribeBasketDetailProductResponse.of(
                            tuple.get(0, String.class),     // productCode
                            tuple.get(1, String.class),     // productName
                            tuple.get(2, int.class)         // price
                    )
            );
        }

        return res;
    }

    public String findSubscribeCodeByUserIdAndBasketNo(String userId, String basketNo) {
        return oiBasketRepository.findSubscribeCodeByUserIdAndBasketNo(userId, basketNo);
    }

    @Transactional
    public boolean modifySubscribeBasket(OiSubscribeBasketDto dto) {
        OiSubscribeBasket entity = oiBasketRepository.findByUserIdAndBasketNoAndProductIdx(dto.userId(), dto.basketNo(), dto.productIdx());
        System.out.println("entity = " + entity);
        if (entity != null) {
            entity.setDeliveryDate(dto.deliveryDate());
            entity.setProductCode(dto.productCode());
            entity.setQty(dto.qty());
            return true;
        } else {
            return false;
        }
    }
}

