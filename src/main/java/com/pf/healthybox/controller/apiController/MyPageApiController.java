package com.pf.healthybox.controller.apiController;

import com.pf.healthybox.domain.baseInformation.BiUser;
import com.pf.healthybox.dto.request.baseInformationReq.BiUserRequest;
import com.pf.healthybox.service.BiUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@RestController
@RequestMapping("/api/mypage")
public class MyPageApiController {

    private final BiUserService biUserService;

    public MyPageApiController(@Autowired BiUserService biUserService) {
        this.biUserService = biUserService;
    }

    @PostMapping("/check-pw/{pw}")
    public Boolean checkPw(@PathVariable String pw, HttpServletRequest request) {
        HttpSession session = request.getSession();
        BiUser entity = (BiUser) session.getAttribute("loginUser");

        return (entity != null && entity.getUserPw().equals(pw));
    }

    @PostMapping("/modify-user")
    public void modifyUserInfo(@RequestBody BiUserRequest dataReq, HttpServletRequest sessionReq) {
        biUserService.modifyUserInfo(dataReq.toDto());
        HttpSession session = sessionReq.getSession();
        session.setAttribute("loginUser", biUserService.showUserInfo(dataReq.userId()));
    }

}
