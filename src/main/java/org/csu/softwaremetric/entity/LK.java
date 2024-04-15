package org.csu.softwaremetric.entity;

import lombok.Data;

@Data
public class LK {
    private String name;
    private double CS;
    /** number of overridden    方法重写数*/
    private double NOO;
    /** number of add           增加方法数量*/
    private double NOA;
    /** specialization index    特征化指数*/
    private double SI;
}
