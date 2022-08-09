package com.pf.healthybox.contoller.apiController;

import com.pf.healthybox.dto.PiDivProductDto;
import com.pf.healthybox.dto.PiDivProductPkDto;
import com.pf.healthybox.dto.response.PiDivProductResponse;
import com.pf.healthybox.repository.PiDivProductRepository;
import com.pf.healthybox.service.PiDivProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/div-product")
public class DivProductApiController {

    private final PiDivProductService piDivProductService;
    private final PiDivProductRepository piDivProductRepository;

    @GetMapping("/divProduct")
    public List<PiDivProductResponse> getDivProducts() {
        return piDivProductService.getAllDivProducts();
    }

    @GetMapping("/test")
    public List<PiDivProductResponse> test() {

        PiDivProductResponse res1 = new PiDivProductResponse("정기구독", List.of("도시락", "샐러드", "건강식품"));
        PiDivProductResponse res2 = new PiDivProductResponse("도시락", List.of("일반 도시락", "다이어트 도시락"));

        List<PiDivProductResponse> result = new ArrayList<>();
        result.add(res1);
        result.add(res2);

        return result;
    }

}
