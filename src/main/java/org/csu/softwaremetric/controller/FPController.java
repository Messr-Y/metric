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

    @PostMapping("/getUFC")
    CommonResponse calUFC(@RequestParam("EI1") int EI1, @RequestParam("EI2") int EI2, @RequestParam("EI3") int EI3,
                          @RequestParam("EO1") int EO1, @RequestParam("EO2") int EO2, @RequestParam("EO3") int EO3,
                          @RequestParam("EQ1") int EQ1, @RequestParam("EQ2") int EQ2, @RequestParam("EQ3") int EQ3,
                          @RequestParam("EIF1") int EIF1, @RequestParam("EIF2") int EIF2, @RequestParam("EIF3") int EIF3,
                          @RequestParam("ILF1") int ILF1, @RequestParam("ILF2") int ILF2, @RequestParam("ILF3") int ILF3) {
        UFC ufc = new UFC(EI1, EI2, EI3, EO1, EO2, EO3, EQ1, EQ2, EQ3, EIF1, EIF2, EIF3, ILF1, ILF2, ILF3);
        return CommonResponse.createForSuccess(fpService.calUFC(ufc));
    }

    @PostMapping("/getVAF")
    CommonResponse calVAF(@RequestParam("ErecoveryI1") int recovery, @RequestParam("communication") int communication, @RequestParam("distribute") int distribute,
                          @RequestParam("performance") int performance, @RequestParam("operation") int operation, @RequestParam("online") int online,
                          @RequestParam("multiple") int multiple, @RequestParam("update") int update, @RequestParam("complex") int complex,
                          @RequestParam("interComplex") int interComplex, @RequestParam("reuse") int reuse, @RequestParam("installation") int installation,
                          @RequestParam("multiplePoints") int multiplePoints, @RequestParam("facilitation") int facilitation) {
        VAF vaf = new VAF(recovery, communication, distribute, performance, operation, online, multiple, update, complex, interComplex, reuse, installation, multiplePoints, facilitation);
        return CommonResponse.createForSuccess(fpService.calVAF(vaf));
    }

    @PostMapping("/getFP")
    CommonResponse calFP(@RequestParam("EI1") int EI1, @RequestParam("EI2") int EI2, @RequestParam("EI3") int EI3,
                         @RequestParam("EO1") int EO1, @RequestParam("EO2") int EO2, @RequestParam("EO3") int EO3,
                         @RequestParam("EQ1") int EQ1, @RequestParam("EQ2") int EQ2, @RequestParam("EQ3") int EQ3,
                         @RequestParam("EIF1") int EIF1, @RequestParam("EIF2") int EIF2, @RequestParam("EIF3") int EIF3,
                         @RequestParam("ILF1") int ILF1, @RequestParam("ILF2") int ILF2, @RequestParam("ILF3") int ILF3,
                         @RequestParam("ErecoveryI1") int recovery, @RequestParam("communication") int communication, @RequestParam("distribute") int distribute,
                         @RequestParam("performance") int performance, @RequestParam("operation") int operation, @RequestParam("online") int online,
                         @RequestParam("multiple") int multiple, @RequestParam("update") int update, @RequestParam("complex") int complex,
                         @RequestParam("interComplex") int interComplex, @RequestParam("reuse") int reuse, @RequestParam("installation") int installation,
                         @RequestParam("multiplePoints") int multiplePoints, @RequestParam("facilitation") int facilitation){
        UFC ufc = new UFC(EI1, EI2, EI3, EO1, EO2, EO3, EQ1, EQ2, EQ3, EIF1, EIF2, EIF3, ILF1, ILF2, ILF3);
        VAF vaf = new VAF(recovery, communication, distribute, performance, operation, online, multiple, update, complex, interComplex, reuse, installation, multiplePoints, facilitation);
        return CommonResponse.createForSuccess(fpService.calFP(ufc, vaf));
    }
}
