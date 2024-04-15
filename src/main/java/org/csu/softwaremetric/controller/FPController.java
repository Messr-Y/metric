package org.csu.softwaremetric.controller;

import org.csu.softwaremetric.common.CommonResponse;
import org.csu.softwaremetric.entity.UFC;
import org.csu.softwaremetric.entity.VAF;
import org.csu.softwaremetric.service.FPService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fp")
public class FPController {
    @Autowired
    private FPService fpService;
    @PostMapping
    CommonResponse calFP(@RequestBody UFC ufc, @RequestBody VAF vaf){
        return CommonResponse.createForSuccess(fpService.calFP(ufc, vaf));
    }
}
