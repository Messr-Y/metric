package org.csu.softwaremetric.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class BasicInfo {
    private String className;
    private String classID;
    private double numOfChild;
    private List<Attribute> Attr;

    private List<Operation> Opera;
    private String father;
    private List<String> son;

}
