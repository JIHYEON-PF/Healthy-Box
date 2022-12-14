package com.pf.healthybox.controller.apiController;

import com.pf.healthybox.dto.request.orderInformationReq.OiSubscribeBasketRequest;
import com.pf.healthybox.service.OiBasketService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/basket")
public class BasketApiController {

    private final OiBasketService oiBasketService;

    public BasketApiController(@Autowired OiBasketService oiBasketService) {
        this.oiBasketService = oiBasketService;
    }

    @PatchMapping("/update-qty")
    public void updateBasketQty(@RequestParam String userId, String productCode, Integer qty) {
        oiBasketService.updateBasketQty(userId, productCode, qty);
    }

    @GetMapping("/price-info/{userId}")
    public List<Integer> getPriceInformation(@PathVariable String userId) {
        return oiBasketService.returnPriceInformation(userId);
    }

    @DeleteMapping("/delete-basket")
    public void deleteBasketItems(@RequestParam String userId, @RequestParam String[] productCode) {
        oiBasketService.deleteBasketItems(userId, productCode);
    }

    @GetMapping("/find-subscribe-code")
    public String findSubscribeCode(@RequestParam String userId, @RequestParam String basketNo) {
        return oiBasketService.findSubscribeCodeByUserIdAndBasketNo(userId, basketNo);
    }

    @PutMapping("/modify-subscribe-basket")
    public boolean modifySubscribeBasket(@RequestBody OiSubscribeBasketRequest req) {
        return oiBasketService.modifySubscribeBasket(req.toDto());
    }

    @DeleteMapping("/delete-subscribe-basket")
    public boolean deleteSubscribeBasketItems(@RequestParam String userId,
                                              @RequestParam String basketNo) {
        return oiBasketService.deleteSubscribeBasketItems(userId, basketNo);
    }

}
