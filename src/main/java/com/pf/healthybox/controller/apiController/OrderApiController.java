package com.pf.healthybox.controller.apiController;

import com.pf.healthybox.dto.request.orderInformationReq.OiOrderDetailRequest;
import com.pf.healthybox.dto.request.orderInformationReq.OiOrderRequest;
import com.pf.healthybox.dto.request.orderInformationReq.OiOrderStatusContentRequest;
import com.pf.healthybox.dto.response.orderInformationRes.OiOrderProductQtyResponse;
import com.pf.healthybox.service.OiOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Slf4j
@RequestMapping("/api/order")
@RestController
public class OrderApiController {

    private final OiOrderService oiOrderService;

    public OrderApiController(@Autowired OiOrderService oiOrderService) {
        this.oiOrderService = oiOrderService;
    }

    //주문내역 삭제
    @DeleteMapping("/deleteOrder")
    public void deleteOrderDetail(@RequestParam String userId, @RequestParam String orderNo) {
        oiOrderService.deleteOrderDetail(userId, orderNo);
    }

    //주문 상태 변경
    @PostMapping("/change-status")
    public void changeStatus(@RequestBody OiOrderStatusContentRequest req) {
        oiOrderService.changeStatus(req);
    }

    @PostMapping("/change-subscribe-status")
    public void changeSubscribeStatus(@RequestBody OiOrderStatusContentRequest req) {
        oiOrderService.changeSubscribeStatus(req);
    }

    //주문 등록
    @PostMapping("/insert-order")
    public void insertOrder(@RequestBody OiOrderRequest req) {
        oiOrderService.insertOrder(req);
    }

    @GetMapping("/setting-price/{codes}")
    public List<Integer> settingPrice(@PathVariable List<String> codes) {
        List<OiOrderProductQtyResponse> foundProduct = new ArrayList<>();

        for (String code : codes) {
            String[] splitCode = code.split("-");
            foundProduct.add(oiOrderService.findOrderInform(splitCode[0], splitCode[1], Integer.parseInt(splitCode[2])));
        }

        return oiOrderService.getOrderPrice(oiOrderService.settingDeliveryCost(foundProduct));
    }

    @PatchMapping("/modify-delivery-date")
    public boolean modifyDeliveryDate(@RequestParam String userId,
                                      @RequestParam String orderNo,
                                      @RequestParam int orderIdx,
                                      @RequestParam String deliveryDate) throws ParseException {
        return oiOrderService.modifyDeliveryDate(userId, orderNo, orderIdx, deliveryDate);
    }

}
