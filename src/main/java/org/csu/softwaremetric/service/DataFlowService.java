package org.csu.softwaremetric.service;

import org.csu.softwaremetric.entity.Circle;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

@Service
public class DataFlowService {
      public int findStr(String fileName, String str) throws IOException {
            int count = 0;
            BufferedReader reader = new BufferedReader(new FileReader(fileName));

            String currentLine = reader.readLine();

            while (currentLine != null) {
                  //count+=findJava2(currentLine,str);
                  // 统计str在当前行中出现的次数
                  count += (currentLine.length() - currentLine.replace(str, "").length()) / str.length();
                  currentLine = reader.readLine();
            }

            reader.close();
            System.out.println(count);
            return count;
      }

      public static int findJava2(String s, String find) {
            int account = 0;
            int index = -1;
            // String find = "java";
            int findLen = find.length();
            index = s.indexOf(find);
            while (true) {
//            截掉前面出现java的字符串
                  s = s.substring(index + findLen);
                  account++;
                  index = s.indexOf(find);
//            判断是否还有java,再获取下一个java的位置
                  if (index == -1) {
                        break;
                  }
            }
            return account;
      }

      public Circle getMcCabe(String file_name) throws IOException {
            //查找开始节点数
            int start_count = findStr(file_name, "<o:Start Id");
            // 查找终止节点数
            int end_count = findStr(file_name, "<o:End Id");
            //查找操作数
            int oper_count = findStr(file_name, "<o:Activity Id");
            // 查找判断数
            int dec_count = findStr(file_name, "<o:Decision Id");
            // 查找边数
            int flow_count = findStr(file_name, "<o:ActivityFlow Id");

            //计算圈复杂度，分别为边数-节点数+2及判断数+1
            int McCabe_2 = flow_count - (oper_count + dec_count + start_count + end_count) + 2;
            int McCabe_3 = dec_count + 1;


            //验证
            if (McCabe_2 == McCabe_3) {
                  System.out.println("McCabe:" + McCabe_2);

                  Circle circle = new Circle((oper_count + dec_count + start_count + end_count), flow_count, McCabe_2);
                  return circle;
            } else {
                  System.out.println("Wrong input!");
                  return null;
            }


      }
}
