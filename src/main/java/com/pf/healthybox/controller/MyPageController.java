package com.pf.healthybox.controller;

import com.pf.healthybox.domain.baseInformation.BiUser;
import com.pf.healthybox.domain.config.DeliveryFlag;
import com.pf.healthybox.domain.orderInformation.OiDeliver;
import com.pf.healthybox.dto.response.orderInformationRes.OiDeliverResponse;
import com.pf.healthybox.dto.response.orderInformationRes.OiOrderDetailResponse;
import com.pf.healthybox.dto.response.orderInformationRes.OiOrderListResponse;
import com.pf.healthybox.dto.response.orderInformationRes.OiOrderSubscribeListResponse;
import com.pf.healthybox.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import retrofit2.http.Path;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RequestMapping("/mypage")
@Controller
public class MyPageController { //마이페이지 관련 페이지에 대한 컨트롤러(주문내역, 장바구니, 배송정보 관리 등)

    private final BiPointService biPointService;
    private final OiDeliverService oiDeliverService;
    private final OiOrderService oiOrderService;
    private final OiBasketService oiBasketService;

    private final HttpServletRequest request;

    public MyPageController(@Autowired BiUserService biUserService,
                            @Autowired BiPointService biPointService,
                            @Autowired OiDeliverService oiDeliverService,
                            @Autowired OiOrderService oiOrderService,
                            @Autowired OiBasketService oiBasketService,
                            HttpServletRequest request) {
        this.biPointService = biPointService;
        this.oiDeliverService = oiDeliverService;
        this.oiOrderService = oiOrderService;
        this.oiBasketService = oiBasketService;
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

    @GetMapping("/order-list/single")
    public String showSingleOrderList(ModelMap model) {

        HttpSession session = request.getSession();
        BiUser entity = (BiUser) session.getAttribute("loginUser");

        if (entity != null) {
            List<OiOrderListResponse> list = oiOrderService.findOrderList(entity.getUserId(), "N");

            model.addAttribute("orderList", list);
            model.addAttribute("separation", "ordered");
        }
        return isLogin("myPageTemplates/myPageOrderList");
    }

    @GetMapping("/order-list/{isSubscribe}/{orderNo}")
    public String showSingleOrderDetail(@PathVariable String orderNo,
                                  @PathVariable String isSubscribe,
                                  ModelMap model) {

        HttpSession session = request.getSession();
        BiUser entity = (BiUser) session.getAttribute("loginUser");

        List<OiOrderDetailResponse> list;

        if (entity != null) {
            if (isSubscribe.equals("single")) {
                list = oiOrderService.findOrderDetails(entity.getUserId(), orderNo, "N");
            } else {
                list = oiOrderService.findOrderDetails(entity.getUserId(), orderNo, "Y");
            }

            model.addAttribute("details", list);
            model.addAttribute("separation", "ordered");
        }

        if (isSubscribe.equals("single")) {
            return isLogin("myPageTemplates/myPageOrderDetail");
        } else {
            return isLogin("myPageTemplates/myPageOrderSubscribeDetail");
        }

    }

    @GetMapping("/order-list/subscribe")
    public String showSubscribeOrderList(ModelMap model) {

        HttpSession session = request.getSession();
        BiUser entity = (BiUser) session.getAttribute("loginUser");

        if (entity != null) {
            List<OiOrderSubscribeListResponse> list = oiOrderService.findSubscribeOrderList(entity.getUserId(), "Y");

            model.addAttribute("orderList", list);
            model.addAttribute("separation", "ordered");
        }
        return isLogin("myPageTemplates/myPageOrderSubscribeList");
    }

    @GetMapping("/tracking")
    public String showTrackingPackages(@RequestParam String code,
                                       @RequestParam String invoice,
                                       ModelMap model) {
        String apiCode = oiOrderService.getLogisticsApiCode();
        List<Object> data = List.of(apiCode, code, invoice);

        if (!apiCode.isBlank() && !code.isBlank() && !invoice.isBlank()) {
            model.addAttribute("data", data);
        }
        return "myPageTemplates/myPageOrderDeliveryCheck";
    }

    @GetMapping("/{isSubscribe}/change-status/{orderNo}")
    public String changeStatus(@PathVariable String isSubscribe,
                               @PathVariable String orderNo,
                               ModelMap model) {
        HttpSession session = request.getSession();
        BiUser entity = (BiUser) session.getAttribute("loginUser");

        List<OiOrderDetailResponse> list;

        if (entity != null) {
            if (isSubscribe.equals("single")) {
                list = oiOrderService.findOrderDetails(entity.getUserId(), orderNo, "N");
            } else {
                list = oiOrderService.findOrderDetails(entity.getUserId(), orderNo, "Y");
            }

            model.addAttribute("details", list);
            model.addAttribute("separation", "ordered");
        }
        return (isSubscribe.equals("single") ? isLogin("myPageTemplates/myPageOrderDetailStatusChange")
                : isLogin("myPageTemplates/myPageOrderSubscribeDetailStatusChange"));
    }

    @GetMapping("/basket/{isSubscribe}")
    public String showBasket(@PathVariable String isSubscribe,
                             ModelMap model) {
        HttpSession session = request.getSession();
        BiUser entity = (BiUser) session.getAttribute("loginUser");

        if (entity != null) {
            model.addAttribute("basketList",
                    (isSubscribe.equals("single") ? oiBasketService.returnBasketList(entity.getUserId())
                            :oiBasketService.returnSubscribeBasketList(entity.getUserId())));
            model.addAttribute("separation", "basket");
        }

        return (isSubscribe.equals("single") ? isLogin("myPageTemplates/myPageBasket") : isLogin("myPageTemplates/myPageSubscribeBasket"));
    }

    @GetMapping("/basket/subscribe/{basketNo}")
    public String showBasketDetail(@PathVariable String basketNo,
                                   ModelMap model) {
        HttpSession session = request.getSession();
        BiUser entity = (BiUser) session.getAttribute("loginUser");

        if (entity != null) {
            model.addAttribute("details", oiBasketService.returnSubscribeBasketDetail(entity.getUserId(), basketNo));
            model.addAttribute("products", oiBasketService.returnSubscribeProducts(entity.getUserId(), basketNo));
            model.addAttribute("separation", "basket");
        }

        return isLogin("myPageTemplates/myPageSubscribeBasketDetail");
    }

    // 로그인 여부를 확인하여 로그인 페이지로 보낼것인지 입력 페이지로 이동할 것인지 판단
    private String isLogin(String location) {

        HttpSession session = request.getSession();
        BiUser entity = (BiUser) session.getAttribute("loginUser");

        return (entity != null ? location : "redirect:/user/login");
    }

}
