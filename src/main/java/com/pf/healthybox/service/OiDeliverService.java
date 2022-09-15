package com.pf.healthybox.service;

import com.pf.healthybox.domain.config.DeliveryFlag;
import com.pf.healthybox.domain.orderInformation.OiDeliver;
import com.pf.healthybox.dto.orderInformationDto.OiDeliverDto;
import com.pf.healthybox.dto.request.orderInformationReq.OiDeliverRequest;
import com.pf.healthybox.repository.OiDeliverRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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

    public Integer getRegisteredCount(String userId) {
        return oiDeliverRepository.countByUserIdAndDeliveryNameLike(userId, "등록 배송지%") + 1;
    }

    public void registerInformation(OiDeliverDto dto) {
        oiDeliverRepository.save(dto.toEntity());
    }

    public void unDefaultingDeliver(String userId) {
        List<OiDeliver> list = oiDeliverRepository.findByUserIdAndDeliveryFlag(userId, DeliveryFlag.DEF);
        if (list.size() > 0) {
            OiDeliver inform = list.get(0);
            inform.setDeliveryFlag(DeliveryFlag.REG);
            oiDeliverRepository.save(inform);
        }
    }

    @Transactional
    public void doDefaultingDeliver(String userId, Long idx) {
        // 원래 기본 배송지로 들어가있는 데이터 변경
        List<OiDeliver> list = oiDeliverRepository.findByUserIdAndDeliveryFlag(userId, DeliveryFlag.DEF);
        if (list.size() > 0) {
            OiDeliver unDefault = list.get(0);
            unDefault.setDeliveryFlag(DeliveryFlag.REG);
            oiDeliverRepository.save(unDefault);
        }

        // 선택한 배송지 기본배송지로 변경
        OiDeliver doDefault = oiDeliverRepository.getReferenceById(idx);
        doDefault.setDeliveryFlag(DeliveryFlag.DEF);
        oiDeliverRepository.save(doDefault);
    }

    @Transactional
    public void deleteDeliveryInform(String userId, Long idx) {
        oiDeliverRepository.deleteByIdxAndUserId(idx, userId);
    }

    public OiDeliver showDeliveryDetail(String userId, Long idx) {
        return oiDeliverRepository.findByIdxAndUserId(idx, userId);
    }

    public void modifyDeliveryInform(OiDeliverDto dto) {
        Long idx = dto.idx();
        String userId = dto.userId();
        OiDeliver deliver = oiDeliverRepository.findByIdxAndUserId(idx, userId);

        if (deliver != null) {
            deliver.setDeliveryName(dto.deliveryName());
            deliver.setZipcode(dto.zipcode());
            deliver.setAddress1(dto.address1());
            deliver.setAddress2(dto.address2());

            oiDeliverRepository.save(deliver);
        }
    }
}
