package com.pf.healthybox.service;

import com.pf.healthybox.domain.baseInformation.BiPoint;
import com.pf.healthybox.repository.BiPointRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class BiPointService {

    private final BiPointRepository biPointRepository;

    public BiPointService(@Autowired BiPointRepository biPointRepository) {
        this.biPointRepository = biPointRepository;
    }

    public List<BiPoint> showPointList(String userId) {
        return biPointRepository.findByUserId(userId);
    }

    public Integer sumAllPoint(String userId) {
        return biPointRepository.findByUserIdAndIsExpired(userId);
    }

    public Integer expirePoint(String userId) {
        return biPointRepository.findByUserIdAndAndIsExpiredAndDateDiff(userId);
    }
}
