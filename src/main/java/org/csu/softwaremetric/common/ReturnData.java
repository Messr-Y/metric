package org.csu.softwaremetric.common;

import lombok.Data;

import java.util.List;

@Data
public class ReturnData<T> {
    private T returnData;
    private List<Link> links;

    public ReturnData() {
    }
}
