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

}
