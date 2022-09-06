package com.pf.healthybox.repository;

import com.pf.healthybox.domain.config.DeliveryFlag;
import com.pf.healthybox.domain.orderInformation.OiDeliver;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OiDeliverRepository extends JpaRepository<OiDeliver, Long> {

    List<OiDeliver> findByUserIdAndDeliveryFlag(String userId, DeliveryFlag flag);
    Integer countByUserIdAndDeliveryNameLike(String userId, String name);

    void deleteByIdxAndUserId(Long idx, String userId);

    OiDeliver findByIdxAndUserId(Long idx, String userId);

    OiDeliver findByIdx(Long idx);
}
