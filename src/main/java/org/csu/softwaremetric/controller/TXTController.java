package org.csu.softwaremetric.controller;

import org.csu.softwaremetric.common.CommonResponse;
import org.csu.softwaremetric.entity.CycleCom;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;

@RestController
@RequestMapping("/txt")
public class TXTController {
    File dest;


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
//        response.getOutputStream().write(script.getBytes("utf-8"));
        return CommonResponse.createForSuccess(script);
    }


    @GetMapping("/getCycleComplexity")
    @ResponseBody
    public CommonResponse getCycleComplexity() {
        String filename = String.valueOf(dest);

//        String filename = "./src/main/java/org/csu/Automation/file/file1.txt";
//          匹配文件名
//            if(filename.matches(".*\\.txt$")) {
//                System.out.println(filename);
//            }

        long codeLines = 0;
        long commentLines = 0;
        long blankLines = 0;
        long cycleComplexity = 0;

        BufferedReader br = null;
        boolean flag = false;

        try {
            br = new BufferedReader(new FileReader(filename));
            String line = "";
            while ((line = br.readLine()) != null) {

                //统计行数
                line = line.trim(); //除去注释前的空格
                if (line.matches("^[ ]*$")) {  //空白行
                    blankLines++;
                } else if (line.startsWith("//")) {
                    commentLines++;
                } else if (line.startsWith("/*") && line.endsWith("*/")) {
                    commentLines++;
                } else if (line.startsWith("/*") && !line.endsWith("*/")) {
                    commentLines++;
                    flag = true;
                } else if (flag) {
                    commentLines++;
                    if (line.endsWith("*/")) {
                        flag = false;
                    }
                } else {
                    codeLines++;
                    //统计圈复杂度
                    if (line.matches("if(.*)") || line.matches("while(.*)") || line.matches("for(.*)") || line.matches("\\band\\b") || line.matches("\\bor\\b") || line.matches("case:(.*)")) {
                        cycleComplexity++;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                    br = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }

        CycleCom cycleCom = new CycleCom(codeLines, commentLines, blankLines, cycleComplexity + 1);
        return CommonResponse.createForSuccess(cycleCom);
    }


    @GetMapping("/getcode")
    @ResponseBody
    public CommonResponse getcode(Model model) {
//        String filename = String.valueOf(dest);
        String filename = "./src/main/java/org/csu/Automation/file/file2.txt";
//          匹配文件名
//            if(filename.matches(".*\\.txt$")) {
//                System.out.println(filename);
//            }

        long codeLines = 0;
        long commentLines = 0;
        long blankLines = 0;
        long cycleComplexity = 0;

        BufferedReader br = null;
        boolean flag = false;

        try {
            br = new BufferedReader(new FileReader(filename));
            String line = "";
            while ((line = br.readLine()) != null) {

                //统计行数
                line = line.trim(); //除去注释前的空格
                if (line.matches("^[ ]*$")) {  //空白行
                    blankLines++;
                } else if (line.startsWith("//")) {
                    commentLines++;
                } else if (line.startsWith("/*") && line.endsWith("*/")) {
                    commentLines++;
                } else if (line.startsWith("/*") && !line.endsWith("*/")) {
                    commentLines++;
                    flag = true;
                } else if (flag) {
                    commentLines++;
                    if (line.endsWith("*/")) {
                        flag = false;
                    }
                } else {
                    codeLines++;
                    //统计圈复杂度
                    if (line.matches("if(.*)") || line.matches("while(.*)") || line.matches("for(.*)") || line.matches("\\band\\b") || line.matches("\\bor\\b") || line.matches("case:(.*)")) {
                        cycleComplexity++;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                    br = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }

        CycleCom cycleCom = new CycleCom(codeLines, commentLines, blankLines, cycleComplexity + 1);
        return CommonResponse.createForSuccess(cycleCom);
    }

    @PostMapping("/getRawCode")
    @ResponseBody
    public CommonResponse getRawCode(@RequestParam String code) {
        String filePath = "temp.txt";

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            bw.write(code);
        } catch (IOException e) {
            e.printStackTrace();
        }

        long codeLines = 0;
        long commentLines = 0;
        long blankLines = 0;
        long cycleComplexity = 0;

        BufferedReader br = null;
        boolean flag = false;

        try {
            br = new BufferedReader(new FileReader(filePath));
            String line = "";
            while ((line = br.readLine()) != null) {

                //统计行数
                line = line.trim(); //除去注释前的空格
                if (line.matches("^[ ]*$")) {  //空白行
                    blankLines++;
                } else if (line.startsWith("//")) {
                    commentLines++;
                } else if (line.startsWith("/*") && line.endsWith("*/")) {
                    commentLines++;
                } else if (line.startsWith("/*") && !line.endsWith("*/")) {
                    commentLines++;
                    flag = true;
                } else if (flag) {
                    commentLines++;
                    if (line.endsWith("*/")) {
                        flag = false;
                    }
                } else {
                    codeLines++;
                    //统计圈复杂度
                    if (line.matches("if(.*)") || line.matches("while(.*)") || line.matches("for(.*)") || line.matches("\\band\\b") || line.matches("\\bor\\b") || line.matches("case:(.*)")) {
                        cycleComplexity++;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                    br = null;
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
        }

        CycleCom cycleCom = new CycleCom(codeLines, commentLines, blankLines, cycleComplexity + 1);
        return CommonResponse.createForSuccess(cycleCom);
    }


}
