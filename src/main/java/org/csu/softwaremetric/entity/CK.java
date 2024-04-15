package org.csu.softwaremetric.entity;

import lombok.Data;

@Data
public class CK {
    private String name;
    /** Weighted Method per class   类加权方法数*/
    private double WMC;
    /** Response For a Class    类的响应数量*/
    private double RFC;
    /** Depth of Inheritance Tree   继承树的深度*/
    private double DIT;
    /** Number Of Children      子类数量*/
    private double NOC;
    /** Coupling Between Objects       对象间的耦合度*/
    private double CBO;
    /** Lack of Cohesion        类缺乏内聚性*/
    private double LCOM;
}
