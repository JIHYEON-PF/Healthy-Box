package com.pf.healthybox.controller;

import com.pf.healthybox.domain.baseInformation.BiUser;
import com.pf.healthybox.dto.response.orderInformationRes.OiOrderProductQtyResponse;
import com.pf.healthybox.service.OiBasketService;
import com.pf.healthybox.service.OiDeliverService;
import com.pf.healthybox.service.OiOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/order")
public class OrderController {

    private final OiOrderService oiOrderService;
    private final OiDeliverService oiDeliverService;
    private final OiBasketService oiBasketService;
    private final HttpServletRequest request;

    public OrderController(@Autowired OiOrderService oiOrderService,
                           OiDeliverService oiDeliverService, OiBasketService oiBasketService, @Autowired HttpServletRequest request) {
        this.oiOrderService = oiOrderService;
        this.oiDeliverService = oiDeliverService;
        this.oiBasketService = oiBasketService;
        this.request = request;
    }

    @GetMapping("/subscribe")
    public String showOrderSubscribePage(@RequestParam String userId,
                                         @RequestParam String basketNo,
                                         ModelMap model) {
        HttpSession session = request.getSession();
        BiUser entity = (BiUser) session.getAttribute("loginUser");

        if (entity != null) {
            model.addAttribute("items", oiBasketService.returnSubscribeBasketDetail(userId, basketNo));
            model.addAttribute("products", oiBasketService.returnSubscribeProducts(userId, basketNo));
            model.addAttribute("delivery", oiDeliverService.getDefaultDelivery(userId));
            model.addAttribute("user", entity);
        }
        return isLogin("orderTemplates/orderSubscribe");
    }

    @GetMapping("/single-item/{products}")
    public String showOrderSingleItemPage(@PathVariable List<String> products, ModelMap model) {

        HttpSession session = request.getSession();
        BiUser entity = (BiUser) session.getAttribute("loginUser");

        if (entity != null) {

            List<OiOrderProductQtyResponse> foundProduct = new ArrayList<>();

            for (String product : products) {
                String[] splitProduct = product.split("-");
                String productCode = splitProduct[0];
                String sellerCode = splitProduct[1];
                int qty = Integer.parseInt(splitProduct[2]);

                try {
                    foundProduct.add(oiOrderService.findOrderInform(productCode, sellerCode, qty));
                } catch (NullPointerException e) {
                    System.out.println("e = " + e.getMessage());
                    //TODO : 품목을 찾지 못했을 경우 품목을 찾을 수 없다는 에러페이지로 이동
                    //return "nullPage";
                }
            }

            model.addAttribute("products", oiOrderService.settingDeliveryCost(foundProduct));
            model.addAttribute("delivery", oiDeliverService.getDefaultDelivery(entity.getUserId()));
            model.addAttribute("user", entity);
            model.addAttribute("payInform", oiOrderService.getOrderPrice(foundProduct));
        }
        return isLogin("orderTemplates/orderSingleItem");
    }

    @GetMapping("/order-delivery/{isSubscribe}/{userId}")
    public String getDeliveryInform(@PathVariable String isSubscribe,
                                    @PathVariable String userId,
                                    ModelMap model) {
        model.addAttribute("default", oiDeliverService.getDefaultDelivery(userId));
        model.addAttribute("registered", oiDeliverService.getRegisteredDelivery(userId));
        return "orderTemplates/orderDeliveryInfo";
    }

    // 로그인 여부를 확인하여 로그인 페이지로 보낼것인지 입력 페이지로 이동할 것인지 판단
    private String isLogin(String location) {

        HttpSession session = request.getSession();
        BiUser entity = (BiUser) session.getAttribute("loginUser");

        return (entity != null ? location : "redirect:/user/login");
    }

}
