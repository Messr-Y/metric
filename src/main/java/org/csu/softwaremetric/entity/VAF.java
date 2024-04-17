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
    private int multiplePoints;

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
        // 多点运行
        multiplePoints = 0;
        // 易变性
        facilitation = 0;
    }

    public VAF(int recovery, int communication, int distribute, int performance, int operation, int online, int multiple,
               int update, int complex, int interComplex, int reuse, int installation, int multiplePoints, int facilitation) {
        // 系统恢复
        this.recovery = recovery;
        // 数据通讯
        this.communication = communication;
        // 分布式数据处理
        this.distribute = distribute;
        // 性能
        this.performance = performance;
        // 资源需求
        this.operation = operation;
        // 在线数据输入
        this.online = online;
        // 多点运行
        this.multiple = multiple;
        // 在线升级
        this.update = update;
        // 复杂处理
        this.complex = complex;
        // 内部处理复杂
        this.interComplex = interComplex;
        // 可重用性
        this.reuse = reuse;
        // 易安装性
        this.installation = installation;
        // 多点运行
        this.multiplePoints = multiplePoints;
        // 易变性
        this.facilitation = facilitation;
    }

}
