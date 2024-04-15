package org.csu.softwaremetric.common;

import lombok.Data;

@Data
public class URI {
    private String type;
    private String href;
    private String rel;

    public URI(String type, String href,String rel) {
        this.type = type;
        this.href = href;
        this.rel = rel;
    }
}
