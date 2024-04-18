package org.csu.softwaremetric.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FPDTO {
    // <Simple, Ave, Complex>
    // 外部输入
    private int EI1;
    // 外部输出
    private int EO1;
    // 外部查询
    private int EQ1;
    // 内部文件
    private int EIF1;
    // 外部接口
    private int ILF1;

    private int EI2;
    private int EO2;
    private int EQ2;
    private int EIF2;
    private int ILF2;

    private int EI3;
    private int EO3;
    private int EQ3;
    private int EIF3;
    private int ILF3;


    private int recovery;
    private int communication;
    private int distribute;
    private int performance;
    private int operation;
    private int online;
    private int multiple;
    private int update;
    private int complex;
    private int interComplex;
    private int reuse;
    private int installation;
    private int facilitation;
    private int multiplePoints;
}
