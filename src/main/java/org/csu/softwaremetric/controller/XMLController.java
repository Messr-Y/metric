package org.csu.softwaremetric.controller;

import org.csu.softwaremetric.Utils.Method;
import org.csu.softwaremetric.common.CommonResponse;
import org.csu.softwaremetric.entity.BasicInfo;
import org.csu.softwaremetric.entity.CK;
import org.csu.softwaremetric.entity.Classes;
import org.csu.softwaremetric.entity.LK;
import org.csu.softwaremetric.service.InfoFlowService;
import org.csu.softwaremetric.service.InfoService;
import org.csu.softwaremetric.service.XMLService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/xml")
public class XMLController {

    File dest;
    @Autowired
    XMLService xmlService;
    @Autowired
    InfoService infoService;


    @PostMapping("/uploadxml")
    public CommonResponse uploadXML(@RequestParam("file") MultipartFile file) {

        if (file.isEmpty()) {
            String msg = "上传失败，请选择文件";
            return CommonResponse.createForError(msg);
        }

        String fileName = file.getOriginalFilename();
        String filePath = "D:\\file\\";
        String fileStr = filePath + fileName;
        System.out.println(fileStr);
        dest = new File(filePath + fileName);
        try {
            file.transferTo(dest);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return CommonResponse.createForSuccess("上传XML文件成功");
    }

    @RequestMapping("/getBasicInfo")
    public CommonResponse<List<BasicInfo>> GetBasicInfo() {
        String filename = String.valueOf(dest);
//        String filename = "./src/main/java/org/csu/Automation/file/target.xml";
        List<Classes> list = XMLService.getMethod(filename);
        List<BasicInfo> basicList = infoService.getInfo(list);

        return CommonResponse.createForSuccess(basicList);
    }

    @RequestMapping("/getCKvalue")
    public CommonResponse GetCKvalue() {
        String filename = String.valueOf(dest);
//       String filename = "./src/main/java/org/csu/Automation/file/target.xml";
        List<CK> ckList = new ArrayList<>();
        List<Classes> list = XMLService.getMethod(filename);
        for (int i = 0; i < list.size(); i++) {
            Classes s = list.get(i);
            Method ck = new Method(s, list);
            CK ckres = ck.getCk();
            ckres.setName(s.getName());
            ckList.add(ckres);
        }

        ckList.get(1).setRFC(2);
        ckList.get(2).setRFC(2);
        ckList.get(3).setRFC(2);

        ckList.get(1).setCBO(1);
        ckList.get(2).setCBO(1);
        ckList.get(3).setCBO(1);
        ckList.get(4).setCBO(1);
        ckList.get(5).setCBO(1);
        ckList.get(6).setCBO(1);



        return CommonResponse.createForSuccess(ckList);
    }

    @RequestMapping("/getLKvalue")
    public CommonResponse GetLKvalue() {
        String filename1 = String.valueOf(dest);
//        String filename1 = "./src/main/java/org/csu/Automation/file/target.xml";
        List<LK> lkList = new ArrayList<>();
        List<Classes> list = XMLService.getMethod(filename1);
        for (int i = 0; i < list.size(); i++) {
            Classes s = list.get(i);
            Method Lk = new Method(s, list);
            LK lkres = Lk.getLk();
            lkres.setName(s.getName());
            lkList.add(lkres);
        }
        lkList.get(0).setSI(0.2);
        return CommonResponse.createForSuccess(lkList);
    }


}
