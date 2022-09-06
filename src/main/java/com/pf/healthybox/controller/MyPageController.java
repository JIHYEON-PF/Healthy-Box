package com.pf.healthybox.controller;

import com.pf.healthybox.domain.baseInformation.BiUser;
import com.pf.healthybox.domain.config.DeliveryFlag;
import com.pf.healthybox.domain.orderInformation.OiDeliver;
import com.pf.healthybox.dto.response.orderInformationRes.OiDeliverResponse;
import com.pf.healthybox.service.BiPointService;
import com.pf.healthybox.service.BiUserService;
import com.pf.healthybox.service.OiDeliverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RequestMapping("/mypage")
@Controller
public class MyPageController { //마이페이지 관련 페이지에 대한 컨트롤러(주문내역, 장바구니, 배송정보 관리 등)

    private final BiPointService biPointService;
    private final OiDeliverService oiDeliverService;

    private final HttpServletRequest request;

    public MyPageController(@Autowired BiUserService biUserService,
                            @Autowired BiPointService biPointService,
                            @Autowired OiDeliverService oiDeliverService,
                            HttpServletRequest request) {
        this.biPointService = biPointService;
        this.oiDeliverService = oiDeliverService;
        this.request = request;
    }

    //GET
    @GetMapping("/")
    public String showMyPage(ModelMap model) {
        model.addAttribute("separation", "ordered");
        return isLogin("userTemplates/myPage");
    }

    @GetMapping("/check-pw")
    public String showMyPageCheckPw(@RequestParam String separation, ModelMap model) {

        model.addAttribute("separation", separation);
        return isLogin("userTemplates/myPageCheckPw");
    }

    @GetMapping("/modify-user")
    public String showMyPageModifyUser(ModelMap model) {
        model.addAttribute("separation", "modify");
        return isLogin("userTemplates/myPageModifyUser");
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

        return isLogin("userTemplates/myPagePoint");
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
        return isLogin("userTemplates/myPageDelivery");
    }

    @GetMapping("/deliverInfo")
    public String showDeliverInfo(ModelMap model) {
        model.addAttribute("inform", OiDeliver.of("", "", "", "", "", null));
        return "userTemplates/myPageDeliveryInfo";
    }

    @GetMapping("/deliverInfoDetail")
    public String showDeliverInfoDetail(@RequestParam String userId,
                                        @RequestParam Long idx,
                                        ModelMap model) {
        OiDeliver deliverInform = oiDeliverService.showDeliveryDetail(userId, idx);

        model.addAttribute("inform", deliverInform);
        return "userTemplates/myPageDeliveryInfo";
    }

    // 로그인 여부를 확인하여 로그인 페이지로 보낼것인지 입력 페이지로 이동할 것인지 판단
    private String isLogin(String location) {

        HttpSession session = request.getSession();
        BiUser entity = (BiUser) session.getAttribute("loginUser");

        return (entity != null ? location : "redirect:/user/login");
    }

}
