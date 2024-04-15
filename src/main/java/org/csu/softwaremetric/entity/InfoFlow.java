package org.csu.softwaremetric.entity;

import lombok.Data;

@Data
public class InfoFlow {
    private String methodName;
    private int flowIn;
    private int flowOut;
    private int length;
    private int complexity;
    private int shepperdComplexity;
    private int startLine;
    private int endLine;
    private String source;
    public InfoFlow(String methodName, int startLine, int endLine, String source) {
        this.methodName = methodName;
        this.length = endLine - startLine + 1;
        this.startLine = startLine;
        this.endLine = endLine;
        this.flowIn = 0;
        this.flowOut = 0;
        this.source = source;
    }
    public void updateData(){
        this.shepperdComplexity = this.flowIn * this.flowIn * this.flowOut * this.flowOut;
        this.complexity = this.shepperdComplexity * this.length;
    }
}
