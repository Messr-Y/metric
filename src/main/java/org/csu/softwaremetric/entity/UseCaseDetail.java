package org.csu.softwaremetric.entity;

import lombok.Data;

import java.util.ArrayList;

@Data
public class UseCaseDetail {
    private ArrayList<NameWeight> actorList;
    private ArrayList<NameWeight> useCaseList;
}
