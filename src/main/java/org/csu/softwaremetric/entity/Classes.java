package org.csu.softwaremetric.entity;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Classes {
    private String id;
    private String name;
    private List<Attribute> attributes;
    private List<Operation> operations;
    private Classes father;
    private String generalization;
    private List<Classes> children;
    private List<String> dependencies;
    private List<String> associations;

    public Classes() {
        super();
        attributes = new ArrayList<>();
        operations = new ArrayList<>();
        dependencies = new ArrayList<>();
        associations = new ArrayList<>();
        children = new ArrayList<>();
    }

    public void addAttribute(Attribute attribute){
        attributes.add(attribute);
    }

    public void addOperation(Operation operation){
        operations.add(operation);
    }

    public void addChildren(Classes targetClass){
        children.add(targetClass);
    }


}
