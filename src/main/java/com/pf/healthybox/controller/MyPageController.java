package com.pf.healthybox.controller;

import com.pf.healthybox.domain.baseInformation.BiUser;
import com.pf.healthybox.domain.config.DeliveryFlag;
import com.pf.healthybox.domain.orderInformation.OiDeliver;
import com.pf.healthybox.dto.response.orderInformationRes.OiDeliverResponse;
import com.pf.healthybox.dto.response.orderInformationRes.OiOrderDetailResponse;
import com.pf.healthybox.dto.response.orderInformationRes.OiOrderListResponse;
import com.pf.healthybox.service.BiPointService;
import com.pf.healthybox.service.BiUserService;
import com.pf.healthybox.service.OiDeliverService;
import com.pf.healthybox.service.OiOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@RequestMapping("/mypage")
@Controller
public class MyPageController { //마이페이지 관련 페이지에 대한 컨트롤러(주문내역, 장바구니, 배송정보 관리 등)

    private final BiPointService biPointService;
    private final OiDeliverService oiDeliverService;
    private final OiOrderService oiOrderService;

    private final HttpServletRequest request;

    public MyPageController(@Autowired BiUserService biUserService,
                            @Autowired BiPointService biPointService,
                            @Autowired OiDeliverService oiDeliverService,
                            OiOrderService oiOrderService, HttpServletRequest request) {
        this.biPointService = biPointService;
        this.oiDeliverService = oiDeliverService;
        this.oiOrderService = oiOrderService;
        this.request = request;
    }

    //GET
    @GetMapping("/")
    public String showMyPage(ModelMap model) {
        model.addAttribute("separation", "ordered");
        return isLogin("myPageTemplates/myPage");
    }

    @GetMapping("/check-pw")
    public String showMyPageCheckPw(@RequestParam String separation, ModelMap model) {

        model.addAttribute("separation", separation);
        return isLogin("myPageTemplates/myPageCheckPw");
    }

    @GetMapping("/modify-user")
    public String showMyPageModifyUser(ModelMap model) {
        model.addAttribute("separation", "modify");
        return isLogin("myPageTemplates/myPageModifyUser");
    }

    @GetMapping("/point")
    public String showPointPage(ModelMap model) {
        HttpSession session = request.getSession();
        BiUser entity = (BiUser) session.getAttribute("loginUser");

        if (entity != null) {
            model.addAttribute("pointList", biPointService.showPointList(entity.getUserId()));
            model.addAttribute("recoCode", entity.getRecoCode());
            model.addAttribute("allPoint", biPointService.sumAllPoint(entity.getUserId()));
            model.addAttribute("expirePoint", biPointService.expirePoint(entity.getUserId()));
            model.addAttribute("separation", "point");
        }

        return isLogin("myPageTemplates/myPagePoint");
    }

    @GetMapping("/delivery")
    public String showDeliveryInfo(ModelMap model) {
        HttpSession session = request.getSession();
        BiUser entity = (BiUser) session.getAttribute("loginUser");

        if (entity != null) {
            model.addAttribute("defaultDelivery",
                    (oiDeliverService.getDefaultDelivery(entity.getUserId()) == null
                            ? OiDeliverResponse.of("등록정보 없음", DeliveryFlag.DEF)
                            : oiDeliverService.getDefaultDelivery(entity.getUserId()))
            );
            model.addAttribute("registeredDelivery",
                    (oiDeliverService.getRegisteredDelivery(entity.getUserId()) == null
                            ? OiDeliverResponse.of("등록정보 없음", DeliveryFlag.REG)
                            : oiDeliverService.getRegisteredDelivery(entity.getUserId()))
            );
            model.addAttribute("separation", "delivery");
        }
        return isLogin("myPageTemplates/myPageDelivery");
    }

    @GetMapping("/deliverInfo")
    public String showDeliverInfo(ModelMap model) {

        HttpSession session = request.getSession();
        BiUser entity = (BiUser) session.getAttribute("loginUser");

        model.addAttribute("inform", OiDeliver.of(entity.getUserId(), "", "", "", "", null));
        return "myPageTemplates/myPageDeliveryInfo";
    }

    @GetMapping("/deliverInfoDetail")
    public String showDeliverInfoDetail(@RequestParam String userId,
                                        @RequestParam Long idx,
                                        ModelMap model) {
        OiDeliver deliverInform = oiDeliverService.showDeliveryDetail(userId, idx);

        model.addAttribute("inform", deliverInform);

        return "myPageTemplates/myPageDeliveryInfo";
    }

    @GetMapping("/order-list")
    public String showOrderList(ModelMap model) {

        HttpSession session = request.getSession();
        BiUser entity = (BiUser) session.getAttribute("loginUser");

        if (entity != null) {
            List<OiOrderListResponse> list = oiOrderService.findOrderList(entity.getUserId());

            model.addAttribute("orderList", list);
            model.addAttribute("separation", "ordered");
        }
        return isLogin("myPageTemplates/myPageOrderList");
    }

    @GetMapping("/order-list/{orderNo}")
    public String showOrderDetail(@PathVariable String orderNo,
                                  ModelMap model) {

        HttpSession session = request.getSession();
        BiUser entity = (BiUser) session.getAttribute("loginUser");

        if (entity != null) {
            List<OiOrderDetailResponse> list = oiOrderService.findOrderDetails(entity.getUserId(), orderNo);

            model.addAttribute("details", list);
            model.addAttribute("separation", "ordered");
        }

        return isLogin("myPageTemplates/myPageOrderDetail");

    }

    // 로그인 여부를 확인하여 로그인 페이지로 보낼것인지 입력 페이지로 이동할 것인지 판단
    private String isLogin(String location) {

        HttpSession session = request.getSession();
        BiUser entity = (BiUser) session.getAttribute("loginUser");

        return (entity != null ? location : "redirect:/user/login");
    }

}
