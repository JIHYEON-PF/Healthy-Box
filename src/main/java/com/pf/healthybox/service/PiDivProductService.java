package com.pf.healthybox.service;

import com.pf.healthybox.dto.response.productInformationRes.PiDivProductResponse;
import com.pf.healthybox.repository.PiDivProductRepository;
import com.querydsl.core.Tuple;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Service
public class PiDivProductService {

    private final PiDivProductRepository piDivProductRepository;

    public List<String> getDistinctDivProducts() {
        return piDivProductRepository.findAllDistinctProductGroup();
    }

    public List<PiDivProductResponse> getAllDivProducts() {

        List<Tuple> query = piDivProductRepository.findAllProductGroup();

        List<PiDivProductResponse> result = new ArrayList<>();
        List<String> values = new ArrayList<>();
        int j = 0; // 비교 임시 변수

        for (int i = 0 ; i < query.size() ; i++) {

            String key = query.get(i).get(0, String.class);

            if (i == 0) {
                values.add(query.get(i).get(1, String.class));
            } else if (key.equals(query.get(j).get(0, String.class))) {
                values.add(query.get(i).get(1, String.class));
            } else {
                result.add(new PiDivProductResponse(query.get(j).get(0, String.class), new ArrayList<>(values)));
                values.removeAll(values);
                values.add(query.get(i).get(1, String.class));
                j = i;
            }

            if (i == query.size() - 1) {
                result.add(new PiDivProductResponse(key, new ArrayList<>(values)));
            }

        }

        return result;

    }

}
