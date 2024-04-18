package org.csu.softwaremetric.controller;

import org.csu.softwaremetric.common.CommonResponse;
import org.csu.softwaremetric.entity.UFC;
import org.csu.softwaremetric.entity.VAF;
import org.csu.softwaremetric.service.FPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fp")
public class FPController {
    @Autowired
    private FPService fpService;

    private UFC ufc;

    @PostMapping("/getUFC")
    @ResponseBody
    CommonResponse calUFC(UFC ufc) {
        return CommonResponse.createForSuccess(fpService.calUFC(ufc));
    }

    @PostMapping("/getVAF")
    CommonResponse calVAF(VAF vaf) {
        return CommonResponse.createForSuccess(fpService.calVAF(vaf));
    }

    @PostMapping("/getFP")
    CommonResponse calFP(UFC ufc, VAF vaf){
        return CommonResponse.createForSuccess(fpService.calFP(ufc, vaf));
    }
}
