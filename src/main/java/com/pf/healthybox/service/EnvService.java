package com.pf.healthybox.service;

import com.pf.healthybox.repository.EnvironmentRepository;
import com.querydsl.core.Tuple;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Slf4j
@Service
public class EnvService {

    private final EnvironmentRepository environmentRepository;

    public EnvService(EnvironmentRepository environmentRepository) {
        this.environmentRepository = environmentRepository;

    }

    /**
     * getEnvironmentMapping()을 통해 조회할 수 있는 ApiKey
     * 1. logisticsApiCode - 택배 조회 API
     * 2. iamportApiKey - Iamport ApiKey
     * 3. iamportApiSecret - Iamport ApiSecret
     * 4. iamportIMP - Iamport 결제모듈에 사용되는 IMP 코드
     * */
    public HashMap<String, String> getEnvironmentMapping() {
        HashMap<String, String> result = new HashMap<>();

        List<Tuple> tuples = environmentRepository.fetchEnvData();

        tuples.forEach(tuple -> {
            result.put(tuple.get(0, String.class),
                    tuple.get(1, String.class));
        });

        return result;
    }

}
