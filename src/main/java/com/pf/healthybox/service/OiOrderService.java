package com.pf.healthybox.service;

import com.pf.healthybox.domain.config.PayMethod;
import com.pf.healthybox.domain.config.Status;
import com.pf.healthybox.domain.productInformation.PiProduct;
import com.pf.healthybox.dto.request.orderInformationReq.OiOrderRequest;
import com.pf.healthybox.dto.request.orderInformationReq.OiOrderStatusContentRequest;
import com.pf.healthybox.dto.response.orderInformationRes.OiOrderDetailResponse;
import com.pf.healthybox.dto.response.orderInformationRes.OiOrderListResponse;
import com.pf.healthybox.dto.response.orderInformationRes.OiOrderProductQtyResponse;
import com.pf.healthybox.repository.EnvironmentRepository;
import com.pf.healthybox.repository.OiOrderRepository;
import com.pf.healthybox.repository.OiOrderStatusContentRepository;
import com.pf.healthybox.repository.PiProductRepository;
import com.querydsl.core.Tuple;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.*;

@Slf4j
@Service
public class OiOrderService {

    private final OiOrderRepository oiOrderRepository;
    private final PiProductRepository piProductRepository;
    private final EnvironmentRepository environmentRepository;
    private final OiOrderStatusContentRepository oiOrderStatusContentRepository;

    public OiOrderService(@Autowired OiOrderRepository oiOrderRepository,
                          @Autowired PiProductRepository piProductRepository,
                          @Autowired EnvironmentRepository environmentRepository,
                          @Autowired OiOrderStatusContentRepository oiOrderStatusContentRepository) {
        this.oiOrderRepository = oiOrderRepository;
        this.piProductRepository = piProductRepository;
        this.environmentRepository = environmentRepository;
        this.oiOrderStatusContentRepository = oiOrderStatusContentRepository;
    }

    @Transactional
    public List<OiOrderListResponse> findOrderList(String userId) {
        List<Tuple> tuples = oiOrderRepository.findOrderList(userId);
        List<OiOrderListResponse> res = new ArrayList<>();

        for (Tuple tuple : tuples) {
            res.add(
                    OiOrderListResponse.of(
                            tuple.get(0, String.class),
                            tuple.get(1, LocalDateTime.class),
                            tuple.get(3, String.class),
                            tuple.get(4, Long.class),
                            tuple.get(5, int.class),
                            tuple.get(2, Status.class).getDescription()
                    )
            );
        }

        return res;
    }

    public List<OiOrderDetailResponse> findOrderDetails(String userId, String orderNo) {
        List<Tuple> tuples = oiOrderRepository.findOrderDetail(userId, orderNo);
        List<OiOrderDetailResponse> res = new ArrayList<>();

        for (Tuple tuple : tuples) {
            res.add(
                    OiOrderDetailResponse.of(
                            tuple.get(0, LocalDateTime.class),
                            tuple.get(1, String.class),
                            tuple.get(2, Status.class).getDescription(),
                            tuple.get(3, String.class),
                            tuple.get(4, String.class),
                            tuple.get(5, int.class),
                            tuple.get(6, int.class),
                            tuple.get(7, int.class),
                            tuple.get(8, int.class),
                            tuple.get(9, String.class),
                            tuple.get(10, String.class),
                            tuple.get(11, String.class),
                            tuple.get(12, String.class),
                            tuple.get(13, String.class),
                            tuple.get(14, String.class),
                            tuple.get(15, PayMethod.class).getDescription(),
                            tuple.get(16, int.class),
                            tuple.get(17, int.class),
                            tuple.get(18, int.class)
                    )
            );
        }
        return res;
    }

    public String getLogisticsApiCode() {
        return environmentRepository.findLogisticsApiCode();
    }

    @Transactional
    public void deleteOrderDetail(String userId, String orderNo) {
        oiOrderRepository.deleteOrderDetail(userId, orderNo);
    }

    @Transactional
    public void changeStatus(OiOrderStatusContentRequest req) {
        req = OiOrderStatusContentRequest.of(
                req.orderNo(),
                oiOrderRepository.findSellerCodeByOrderNo(req.orderNo()),
                req.division(),
                req.title(),
                req.content()
            );
        oiOrderStatusContentRepository.save(req.toDto().toEntity());

        String statusCode = req.division().toUpperCase();

        if (!statusCode.equals("")) {
            oiOrderRepository.updateStatusCode(req.orderNo(), req.sellerCode(), statusCode);
        }
    }

    public void insertOrder(OiOrderRequest req) {
        oiOrderRepository.save(req.toDto().toEntity());
    }

    public OiOrderProductQtyResponse findOrderInform(String productCode, String sellerCode, int qty) {
        PiProduct product = piProductRepository.findProducts(productCode, sellerCode);
        return OiOrderProductQtyResponse.of(product.getPiProductPk().getProductCode(),
                product.getPiProductPk().getSellerCode(),
                product.getProductName(),
                product.getPrice(),
                qty,
                product.getDcCost(),
                0);
    }

    public List<OiOrderProductQtyResponse> settingDeliveryCost(List<OiOrderProductQtyResponse> foundProduct) {
        Set<String> sellerCodeSet = new HashSet<>();
        HashMap<String, Integer> hashMap = new HashMap<>();

        foundProduct.forEach((product) -> sellerCodeSet.add(product.sellerCode()));
        sellerCodeSet.forEach((key) -> hashMap.put(key, 0));

        for (OiOrderProductQtyResponse product : foundProduct) {
            hashMap.put(product.sellerCode(), hashMap.get(product.sellerCode()) + ((product.unitCost() - product.dcCost()) * product.qty()));
        }

        for (int i = 0 ; i < foundProduct.size() ; i++) {
            if (hashMap.containsKey(foundProduct.get(i).sellerCode()) && hashMap.get(foundProduct.get(i).sellerCode()) != 0) {
                if (hashMap.get(foundProduct.get(i).sellerCode()) < 50000) {
                    foundProduct.set(i, OiOrderProductQtyResponse.of(
                            foundProduct.get(i).productCode(),
                            foundProduct.get(i).sellerCode(),
                            foundProduct.get(i).productName(),
                            foundProduct.get(i).unitCost(),
                            foundProduct.get(i).qty(),
                            foundProduct.get(i).dcCost(),
                            3000
                    ));
                    hashMap.replace(foundProduct.get(i).sellerCode(), 0);
                }
            }
        }

        return foundProduct;

    }

    public List<Integer> getOrderPrice(List<OiOrderProductQtyResponse> foundProduct) {
        int totalUnitCost = 0;
        int totalDeliverCost = 0;
        int totalDcCost = 0;

        for (OiOrderProductQtyResponse product : foundProduct) {
            totalUnitCost += product.unitCost() * product.qty();
            totalDeliverCost += product.deliveryCost();
            totalDcCost += product.dcCost();
        }

        return List.of(totalUnitCost, totalDeliverCost, totalDcCost, totalUnitCost + totalDeliverCost - totalDcCost);
    }
}
