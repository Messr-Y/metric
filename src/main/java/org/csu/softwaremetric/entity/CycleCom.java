package org.csu.softwaremetric.entity;


import lombok.Data;

@Data
public class CycleCom {
      public CycleCom(long codeLines, long commentLines, long blankLines, long cycleComplexity) {
            this.codeLines = codeLines;
            this.commentLines = commentLines;
            this.blankLines = blankLines;
            this.cycleComplexity = cycleComplexity;
            this.commentDensity = (double) commentLines / (commentLines + codeLines);
      }

      private long codeLines;
      private long commentLines;
      private long blankLines;
      private long cycleComplexity;
      private double commentDensity;
}
