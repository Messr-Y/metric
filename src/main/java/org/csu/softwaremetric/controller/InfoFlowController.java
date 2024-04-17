package org.csu.softwaremetric.controller;

import org.csu.softwaremetric.common.CommonResponse;
import org.csu.softwaremetric.entity.CycleCom;
import org.csu.softwaremetric.entity.InfoFlow;
import org.csu.softwaremetric.service.InfoFlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.List;

@RestController
@RequestMapping("/flow")
public class InfoFlowController {
    File dest;

    @Autowired
    private InfoFlowService infoFlowService;

    @PostMapping(value = "/uploadtxt")
    @ResponseBody
    CommonResponse uploadTXT(@RequestParam("txt") MultipartFile file) throws IOException {
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

    @PostMapping("/getRawCode")
    @ResponseBody
    public CommonResponse getRawCode(@RequestBody String code) {
        String filePath = "temp1.txt";

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            bw.write(code);
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<InfoFlow> methods = infoFlowService.getInfoFlows(filePath);

        if (methods == null) {
            return CommonResponse.createForError();
        }

        return CommonResponse.createForSuccess(methods);
    }

    @GetMapping("/getInfoFlow")
    @ResponseBody
    public CommonResponse getInfoFlow() {
        String filename = String.valueOf(dest);
        List<InfoFlow> methods = infoFlowService.getInfoFlows(filename);

        if (methods == null) {
            return CommonResponse.createForError();
        }

        return CommonResponse.createForSuccess(methods);
    }
}
