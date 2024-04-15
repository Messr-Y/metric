package org.csu.softwaremetric.controller;


import org.csu.softwaremetric.common.CommonResponse;
import org.csu.softwaremetric.entity.Circle;
import org.csu.softwaremetric.entity.UseCase;
import org.csu.softwaremetric.entity.UseCaseDetail;
import org.csu.softwaremetric.service.DataFlowService;
import org.csu.softwaremetric.service.UseCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/oom")
public class OMMController {

    File dest;

    @Autowired
    UseCaseService useCaseService;
    @Autowired
    DataFlowService dataFlowService;

    @PostMapping(value = "/uploadoom")
    @ResponseBody
    CommonResponse uploadOMM(@RequestParam("file") MultipartFile file) throws IOException {
        String script = "上传失败";

        if (file.isEmpty()) {
            script = "<script>alert('上传失败，请选择文件!');</script>";
        }

        String fileName = file.getOriginalFilename();
        String filePath = "D:\\file\\";
        dest = new File(filePath + fileName);
        try {
            file.transferTo(dest);
            script = "<script>alert('上传成功!');</script>";
        } catch (IOException e) {
            e.printStackTrace();
        }

        return CommonResponse.createForSuccess(script);
    }

    @GetMapping("/userCase")
    public CommonResponse getUserCase() {
        UseCase useCase = useCaseService.getActorAndUseCaseCount(String.valueOf(dest));

        return CommonResponse.createForSuccess(useCase);
    }

    @GetMapping("/userCaseDetail")
    public CommonResponse getUserCaseDetail() {
        UseCaseDetail useCaseDetail = useCaseService.getActorAndUseCaseDetail(String.valueOf(dest));

        return CommonResponse.createForSuccess(useCaseDetail);
    }

    @GetMapping("/dataFlow")
    public CommonResponse getMCabe() throws IOException {


        Circle circle = dataFlowService.getMcCabe(String.valueOf(dest));

        if (circle != null)
            return CommonResponse.createForSuccess(circle);
        else
            return CommonResponse.createForError();
    }
}
