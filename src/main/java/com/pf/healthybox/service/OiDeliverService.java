package com.pf.healthybox.service;

import com.pf.healthybox.domain.config.DeliveryFlag;
import com.pf.healthybox.domain.orderInformation.OiDeliver;
import com.pf.healthybox.repository.OiDeliverRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class OiDeliverService {

    private final OiDeliverRepository oiDeliverRepository;

    public OiDeliverService(@Autowired OiDeliverRepository oiDeliverRepository) {
        this.oiDeliverRepository = oiDeliverRepository;
    }

    public OiDeliver getDefaultDelivery(String userId) {

        List<OiDeliver> list = oiDeliverRepository.findByUserIdAndDeliveryFlag(userId, DeliveryFlag.DEF);

        return (list.isEmpty() ? null : list.get(0));
    }

    public List<OiDeliver> getRegisteredDelivery(String userId) {

        List<OiDeliver> list = oiDeliverRepository.findByUserIdAndDeliveryFlag(userId, DeliveryFlag.REG);

        return (list.isEmpty() ? null : list);
    }
}
