package com.pf.healthybox.contoller.apiController;

import com.pf.healthybox.dto.request.orderInformationReq.OiDeliverRequest;
import com.pf.healthybox.service.OiDeliverService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/api/deliver")
@RestController
public class DeliverInfoApiController {

    private final OiDeliverService oiDeliverService;

    public DeliverInfoApiController(@Autowired OiDeliverService oiDeliverService) {
        this.oiDeliverService = oiDeliverService;
    }

    @GetMapping("/get-count/{userId}")
    public Integer getRegisteredCount(@PathVariable String userId) {
        return oiDeliverService.getRegisteredCount(userId);
    }

    @PostMapping("/register")
    public void registerInformation(@RequestBody OiDeliverRequest req) {
        oiDeliverService.registerInformation(req.toDto());
    }

    @PatchMapping("/undefault/{userId}")
    public void unDefaulting(@PathVariable String userId) {
        oiDeliverService.unDefaultingDeliver(userId);
    }

    @PatchMapping("/dodefault")
    public void doDefaulting(@RequestParam String userId, @RequestParam Long idx) {
        oiDeliverService.doDefaultingDeliver(userId, idx);
    }

    @DeleteMapping("/delete")
    public void deleteDeliveryInform(@RequestParam String userId, @RequestParam Long idx) {
        oiDeliverService.deleteDeliveryInform(userId, idx);
    }

    @PutMapping("/modify")
    public void modifyDeliveryInform(@RequestBody OiDeliverRequest req) {
        oiDeliverService.modifyDeliveryInform(req.toDto());
    }

}
