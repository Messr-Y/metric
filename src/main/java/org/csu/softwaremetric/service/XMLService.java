package org.csu.softwaremetric.service;

import org.springframework.stereotype.Service;

import org.csu.softwaremetric.entity.Association;

import org.csu.softwaremetric.entity.Classes;
import org.csu.softwaremetric.entity.Dependency;
import org.dom4j.Element;

import org.csu.softwaremetric.Utils.XMLUtil;
import org.dom4j.Document;

import java.util.ArrayList;
import java.util.List;

@Service
public class XMLService {

    public static List<Classes> getMethod(String filename) {
        // String filename = String.valueOf(new File("/Users/yenan/IdeaProjects/SoftwareMetrics/src/test/java/org/csu/Automation/target.xml"));
        XMLUtil xmlUtil = new XMLUtil();
        Document document = xmlUtil.load(filename);
        Element rootNode = document.getRootElement();
        List<Element> list = rootNode.elements("packagedElement");


        List<Classes> classList = new ArrayList<>();
        List<Association> associationList = new ArrayList<>();
        List<Dependency> dependencyList = new ArrayList<>();

        for (Element element : list) { //循环输出全部packagedElement的相关信息
            XMLUtil.analysisXML(element, classList, associationList, dependencyList);
            List<Element> list2 = element.elements(); //得到emp元素下的子元素
            for (Element e : list2) {  //遍历emp元素下的子元素
                XMLUtil.analysisXML(e, classList, associationList, dependencyList);
            }
        }
        List<Classes> list1 = classList;

        xmlUtil.clean(classList);
        return classList;
    }


    public static List<Association> getAssociation(String filename) {
        // String filename = String.valueOf(new File("/Users/yenan/IdeaProjects/SoftwareMetrics/src/test/java/org/csu/Automation/target.xml"));
        XMLUtil xmlUtil = new XMLUtil();
        Document document = xmlUtil.load(filename);
        Element rootNode = document.getRootElement();
        List<Element> list = rootNode.elements("packagedElement");


        List<Classes> classList = new ArrayList<>();
        List<Association> associationList = new ArrayList<>();
        List<Dependency> dependencyList = new ArrayList<>();

        for (Element element : list) { //循环输出全部packagedElement的相关信息
            XMLUtil.analysisXML(element, classList, associationList, dependencyList);
            List<Element> list2 = element.elements(); //得到emp元素下的子元素
            for (Element e : list2) {  //遍历emp元素下的子元素
                XMLUtil.analysisXML(e, classList, associationList, dependencyList);
            }
        }
        return associationList;
    }


    public static List<Dependency> getDependency(String filename) {
        // String filename = String.valueOf(new File("/Users/yenan/IdeaProjects/SoftwareMetrics/src/test/java/org/csu/Automation/target.xml"));
        XMLUtil xmlUtil = new XMLUtil();
        Document document = xmlUtil.load(filename);
        Element rootNode = document.getRootElement();
        List<Element> list = rootNode.elements("packagedElement");


        List<Classes> classList = new ArrayList<>();
        List<Association> associationList = new ArrayList<>();
        List<Dependency> dependencyList = new ArrayList<>();

        for (Element element : list) { //循环输出全部packagedElement的相关信息
            XMLUtil.analysisXML(element, classList, associationList, dependencyList);
            List<Element> list2 = element.elements(); //得到emp元素下的子元素
            for (Element e : list2) {  //遍历emp元素下的子元素
                XMLUtil.analysisXML(e, classList, associationList, dependencyList);
            }
        }
        return dependencyList;
    }
}
