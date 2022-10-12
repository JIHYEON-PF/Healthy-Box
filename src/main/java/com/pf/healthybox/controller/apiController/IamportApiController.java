package com.pf.healthybox.controller.apiController;

import com.pf.healthybox.dto.request.orderInformationReq.OiOrderCancelDataRequest;
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.request.CancelData;
import com.siot.IamportRestClient.response.AccessToken;
import com.siot.IamportRestClient.response.Certification;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/iamport")
public class IamportApiController extends IamportClient {

    public IamportApiController() {
        // TODO : apikey와 apisecert은 DB에서 조회해서 가져올 수 있도록 수정
        super("0636024527663452", "daLHd2j6oV2t0tDOAGPolrumg202pMqqxdMeVTN7g1ycWcwCboUievQJaZJxEPDgN72XMEXWyjBioIfm");
    }

    //토큰 발급
    @PostMapping("/getToken")
    public IamportResponse<AccessToken> getToken() throws IamportResponseException, IOException {
        return getAuth();
    }

    //인증정보 조회
    @GetMapping("/getCertification/{impUid}")
    public HashMap<String, String> getCertificationByImpUid(@PathVariable String impUid) throws IamportResponseException, IOException {
        IamportResponse<Certification> certification = super.certificationByImpUid(impUid);
        SimpleDateFormat sdf = new SimpleDateFormat("yyMMdd");

        HashMap<String, String> res = new HashMap<>();

        res.put("name", certification.getResponse().getName());
        res.put("phone", certification.getResponse().getPhone());
        res.put("birth", sdf.format(certification.getResponse().getBirth()));

        return res;
    }

    //결제 취소
    @PostMapping("/cancel-payment")
    public HashMap<String, Object> cancelPaymentByMerchantUid(@RequestBody OiOrderCancelDataRequest req) throws IamportResponseException, IOException {
        CancelData cancelData = new CancelData(req.merchantUid(), false);
        cancelData.setReason(req.reason());
        cancelData.setRefund_holder(req.refundHolder());
        cancelData.setRefund_account(req.refundAccount());
        cancelData.setRefund_bank(req.refundBank());

        /*
        * REST API(POST https://api.iamport.kr/payments/cancel) 요청에 대한 응답 코드가 200이라도
        * 응답 body의 code가 0이 아니면 환불에 실패했다는 의미입니다.
        * 실패 사유는 body의 message를 통해 확인하실 수 있습니다.
        *  */

        IamportResponse<Payment> response = cancelPaymentByImpUid(cancelData);

        HashMap<String, Object> result = new HashMap<>();
        result.put("code", response.getCode());
        result.put("msg", response.getMessage());

        return result;
    }

}
