package com.pf.healthybox.controller.apiController;

import com.pf.healthybox.dto.request.orderInformationReq.OiOrderStatusContentRequest;
import com.pf.healthybox.service.OiOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

}
