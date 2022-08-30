package com.pf.healthybox.contoller;

import com.pf.healthybox.domain.baseInformation.BiUser;
import com.pf.healthybox.service.BiPointService;
import com.pf.healthybox.service.BiUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@RequestMapping("/mypage")
@Controller
public class MyPageController { //마이페이지 관련 페이지에 대한 컨트롤러(주문내역, 장바구니, 배송정보 관리 등)

    private final BiUserService biUserService;
    private final BiPointService biPointService;
    private final HttpServletRequest request;

    public MyPageController(@Autowired BiUserService biUserService,
                            @Autowired BiPointService biPointService,
                            HttpServletRequest request) {
        this.biUserService = biUserService;
        this.biPointService = biPointService;
        this.request = request;
    }

    //GET
    @GetMapping("/")
    public String showMyPage() {
        return isLogin("userTemplates/myPage");
    }

    @GetMapping("/check-pw")
    public String showMyPageCheckPw() {
        return isLogin("userTemplates/myPageCheckPw");
    }

    @GetMapping("/modify-user")
    public String showMyPageModifyUser(ModelMap model) {
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
        }

        return isLogin("userTemplates/myPagePoint");
    }

    // 로그인 여부를 확인하여 로그인 페이지로 보낼것인지 입력 페이지로 이동할 것인지 판단
    private String isLogin(String location) {

        HttpSession session = request.getSession();
        BiUser entity = (BiUser) session.getAttribute("loginUser");

        return (entity != null ? location : "redirect:/user/login");
    }

}
