package org.csu.softwaremetric.entity;

import lombok.Data;

import java.util.List;
@Data
public class Operation {
    private String name;
    private String visibility;
    private List<Parameter> parameters;
}
