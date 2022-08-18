package com.pf.healthybox.contoller.apiController;

import com.pf.healthybox.dto.response.productInformationRes.PiDivProductResponse;
import com.pf.healthybox.repository.PiDivProductRepository;
import com.pf.healthybox.service.PiDivProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

}
