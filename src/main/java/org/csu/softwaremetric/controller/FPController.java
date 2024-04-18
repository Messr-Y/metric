package org.csu.softwaremetric.controller;

import org.csu.softwaremetric.common.CommonResponse;
import org.csu.softwaremetric.entity.FPDTO;
import org.csu.softwaremetric.entity.UFC;
import org.csu.softwaremetric.entity.VAF;
import org.csu.softwaremetric.service.FPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
    CommonResponse calFP(FPDTO fpdto) {
        // 给 fpdto 对象转换为 ufc 和 vaf 对象
        UFC ufc = new UFC();
        VAF vaf = new VAF();
        ufc.setEI1(fpdto.getEI1());
        ufc.setEI2(fpdto.getEI2());
        ufc.setEI3(fpdto.getEI3());
        ufc.setEO1(fpdto.getEO1());
        ufc.setEO2(fpdto.getEO2());
        ufc.setEO3(fpdto.getEO3());
        ufc.setEQ1(fpdto.getEQ1());
        ufc.setEQ2(fpdto.getEQ2());
        ufc.setEQ3(fpdto.getEQ3());
        ufc.setEIF1(fpdto.getEIF1());
        ufc.setEIF2(fpdto.getEIF2());
        ufc.setEIF3(fpdto.getEIF3());
        ufc.setILF1(fpdto.getILF1());
        ufc.setILF2(fpdto.getILF2());
        ufc.setILF3(fpdto.getILF3());
        vaf.setRecovery(fpdto.getRecovery());
        vaf.setCommunication(fpdto.getCommunication());
        vaf.setDistribute(fpdto.getDistribute());
        vaf.setPerformance(fpdto.getPerformance());
        vaf.setOperation(fpdto.getOperation());
        vaf.setOnline(fpdto.getOnline());
        vaf.setMultiple(fpdto.getMultiple());
        vaf.setUpdate(fpdto.getUpdate());
        vaf.setComplex(fpdto.getComplex());
        vaf.setInterComplex(fpdto.getInterComplex());
        vaf.setReuse(fpdto.getReuse());
        vaf.setInstallation(fpdto.getInstallation());
        vaf.setFacilitation(fpdto.getFacilitation());
        vaf.setMultiplePoints(fpdto.getMultiplePoints());
        return CommonResponse.createForSuccess(fpService.calFP(ufc, vaf));
    }
}
