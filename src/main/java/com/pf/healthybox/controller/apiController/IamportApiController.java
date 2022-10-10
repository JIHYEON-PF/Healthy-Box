package com.pf.healthybox.controller.apiController;

import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.AccessToken;
import com.siot.IamportRestClient.response.Certification;
import com.siot.IamportRestClient.response.IamportResponse;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/iamport")
public class IamportApiController extends IamportClient {

    public IamportApiController() {
        super("0636024527663452", "daLHd2j6oV2t0tDOAGPolrumg202pMqqxdMeVTN7g1ycWcwCboUievQJaZJxEPDgN72XMEXWyjBioIfm");
    }

    @PostMapping("/getToken")
    public IamportResponse<AccessToken> getToken() throws IamportResponseException, IOException {
        return getAuth();
    }

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

}
