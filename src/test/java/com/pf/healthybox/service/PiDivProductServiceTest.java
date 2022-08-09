package com.pf.healthybox.service;

import com.pf.healthybox.dto.response.PiDivProductResponse;
import com.pf.healthybox.repository.PiDivProductRepository;
import com.querydsl.core.Tuple;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@DisplayName("비즈니스 로직 - 품목분류")
@ExtendWith(MockitoExtension.class)
class PiDivProductServiceTest {

    @InjectMocks private PiDivProductService sut;

    @Mock private PiDivProductRepository piDivProductRepository;

    @DisplayName("1. 헤더에 품목 분류 출력")
    @Test
    void givenNoting_whenRequestingDivProducts_thenReturningDivProducts() {

        //given
        List<String> expectedDivProducts = List.of("정기구독", "샐러드", "도시락");
        given(piDivProductRepository.findAllDistinctProductGroup()).willReturn(expectedDivProducts);

        //when
        List<String> actualDivProducts = sut.getDistinctDivProducts();

        //then
        assertThat(actualDivProducts).isEqualTo(expectedDivProducts);
        then(piDivProductRepository).should().findAllDistinctProductGroup();

    }

}