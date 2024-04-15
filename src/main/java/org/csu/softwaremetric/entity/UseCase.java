package org.csu.softwaremetric.entity;

import lombok.Data;

@Data
public class UseCase {
    /** 执行者数量 **/
    private int actorCount;
    /** 用例数量 **/
    private int useCaseCount;
}
