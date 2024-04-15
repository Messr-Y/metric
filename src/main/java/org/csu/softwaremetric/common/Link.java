package org.csu.softwaremetric.common;

import lombok.Data;

@Data
public class Link {
      private String rel;
      private String href;

      public Link(String rel, String href) {
            this.rel = rel;
            this.href = href;
      }
}
