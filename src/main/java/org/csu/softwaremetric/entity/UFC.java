package org.csu.softwaremetric.entity;
import lombok.Data;
import java.util.ArrayList;

@Data
public class UFC {
    // <Simple, Ave, Complex>
    private int EI;
    private int EO;
    private int EQ;
    private int EIF;
    private int ILF;
    private int numEI;
    private int numEO;
    private int numEQ;
    private int numEIF;
    private int numILF;
}
