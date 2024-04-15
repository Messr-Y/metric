package org.csu.softwaremetric.entity;
import lombok.Data;
@Data
public class VAF {
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

    public VAF() {
        // 系统恢复
        recovery = 0;
        // 数据通讯
        communication = 0;
        // 分布式数据处理
        distribute = 0;
        // 性能
        performance = 0;
        // 资源需求
        operation = 0;
        // 在线数据输入
        online = 0;
        // 多点运行
        multiple = 0;
        // 在线升级
        update = 0;
        // 复杂处理
        complex = 0;
        // 内部处理复杂
        interComplex = 0;
        // 可重用性
        reuse = 0;
        // 易安装性
        installation = 0;
        // 易变性
        facilitation = 0;
    }

}
