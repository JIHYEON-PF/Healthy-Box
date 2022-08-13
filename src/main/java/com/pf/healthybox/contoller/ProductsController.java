package com.pf.healthybox.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/items")
@Controller
public class ProductsController {

    @GetMapping("/lists")//TODO: 품목의 분류와 카테고리를 QueryParam으로 받아와서 각 분류와 카테고리를 불러올 수 있도록 메서드 변경 필요
    public String showProdList() {
        return "prodTemplates/productList";
    }

}
