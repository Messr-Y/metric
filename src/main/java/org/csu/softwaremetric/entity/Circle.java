package org.csu.softwaremetric.entity;


import lombok.Data;

@Data
public class Circle {

      private int  node;
      private int  edge;
      private int circleComplex;

      public Circle(int node, int edge, int circleComplex) {
            this.node = node;
            this.edge = edge;
            this.circleComplex = circleComplex;
      }
}
