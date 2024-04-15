package org.csu.softwaremetric.entity;

import lombok.Data;

@Data
public class Dependency {
    private String supplier;
    private String client;
}
