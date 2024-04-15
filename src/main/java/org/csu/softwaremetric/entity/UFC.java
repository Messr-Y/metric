package org.csu.softwaremetric.entity;
import lombok.Data;
import java.util.ArrayList;

@Data
public class UFC {
    // <Simple, Ave, Complex>
    private ArrayList<Integer> EI;
    private ArrayList<Integer> EO;
    private ArrayList<Integer> EQ;
    private ArrayList<Integer> EIF;
    private ArrayList<Integer> ILF;
    private int numEI;
    private int numEO;
    private int numEQ;
    private int numEIF;
    private int numILF;
}
