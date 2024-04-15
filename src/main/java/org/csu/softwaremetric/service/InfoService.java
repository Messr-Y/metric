package org.csu.softwaremetric.service;

import org.csu.softwaremetric.entity.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InfoService {


    public List<BasicInfo> getInfo(List<Classes> list) {
        List<BasicInfo> basicList = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) { //求属性名和可见行
            Classes cl = list.get(i);
            BasicInfo basicInfo = new BasicInfo();
            basicInfo.setClassName(cl.getName());
            basicInfo.setClassID(cl.getId());
            basicInfo.setNumOfChild(cl.getChildren().size());

            if (cl.getAttributes().size() > 0) {
                List<Attribute> li = new ArrayList<>();
                for (int k = 0; k < cl.getAttributes().size(); k++) {
                    Attribute attribute = cl.getAttributes().get(k);
                    li.add(attribute);
                }
                basicInfo.setAttr(li);
            }
//
            if (cl.getOperations().size() > 0) { //求方法名和可见性
                List<Operation> lis = new ArrayList<>();
                for (int k = 0; k < cl.getOperations().size(); k++) {
                    Operation operation = cl.getOperations().get(k);
                    lis.add(operation);
                }
                basicInfo.setOpera(lis);
            }
            if (cl.getGeneralization() != null) {  //求父类
                for (int j = 0; j < list.size(); j++) {
                    Classes t = list.get(j);
                    if (cl.getGeneralization().equals(t.getId())) {
                        basicInfo.setFather(String.valueOf(t.getFather()));
                    }
                }
            }

            if (cl.getChildren().size() > 0) {
                List<Classes> childC = cl.getChildren();
                List<String> son = new ArrayList<>();
                for (int k = 0; k < childC.size(); k++) {
                    son.add(childC.get(k).getName());
                }
                basicInfo.setSon(son);
            }
           basicList.add(basicInfo);
        }
        return basicList;
    }
}
