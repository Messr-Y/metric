package org.csu.softwaremetric.entity;
import lombok.Data;
import java.util.ArrayList;

@Data
public class UFC {
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
    public UFC(){}

    public UFC(int EI1, int EI2, int EI3, int EO1, int EO2, int EO3,
               int EQ1, int EQ2, int EQ3, int EIF1, int EIF2, int EIF3,
               int ILF1, int ILF2, int ILF3){
        this.EI1 = EI1;
        this.EI2 = EI2;
        this.EI3 = EI3;
        this.EO1 = EO1;
        this.EO2 = EO2;
        this.EO3 = EO3;
        this.EQ1 = EQ1;
        this.EQ2 = EQ2;
        this.EQ3 = EQ3;
        this.EIF1 = EIF1;
        this.EIF2 = EIF2;
        this.EIF3 = EIF3;
        this.ILF1 = ILF1;
        this.ILF2 = ILF2;
        this.ILF3 = ILF3;
    }
}
