package org.csu.softwaremetric.entity;

import lombok.Data;

@Data
public class NameWeight {
    public NameWeight(String name, int weight){
        this.name = name;
        this.weight = weight;
    }
    private String name;
    private int weight;
}
