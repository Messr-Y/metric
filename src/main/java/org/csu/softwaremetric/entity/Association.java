package org.csu.softwaremetric.entity;

import lombok.Data;

@Data
public class Association {
    private String ownedEnd1;
    private String ownedEnd2;
    private String type;
    private String type2;
}
