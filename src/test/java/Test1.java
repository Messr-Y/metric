import org.eclipse.jdt.core.dom.*;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Test1 {
    @Test
    public void test() {
//        String filePath = "D:\\JetBrainProject\\IDEA\\SoftwareMetric\\temp1.txt";
        String filePath = "D:\\JetBrainProject\\IDEA\\SoftwareMetric\\src\\main\\java\\org\\csu\\softwaremetric\\file\\file3.txt"; // 要解析的 Java 文件路径
        try {
            String source = readFileToString(filePath);
            System.out.print(source);
            if (source != null) {
                List<MethodInfo> methods = parseMethods(source);
                Map<String, Integer> fanInMap = new HashMap<>();
                Map<String, Integer> fanOutMap = new HashMap<>();

                for (MethodInfo method : methods) {
                    fanInMap.put(method.getName(), 0);
                    fanOutMap.put(method.getName(), 0);
                }

                for (MethodInfo method : methods) {
                    Set<String> calledMethods = findCalledMethods(method, methods);
                    fanOutMap.put(method.getName(), calledMethods.size());

                    for (String calledMethod : calledMethods) {
                        if (fanInMap.containsKey(calledMethod)) {
                            fanInMap.put(calledMethod, fanInMap.get(calledMethod) + 1);
                        }
                    }
                }
                for(MethodInfo method : methods) {
                    System.out.println(method.getSource());
                }

                // 输出扇入
                System.out.println("Fan-In:");
                for (Map.Entry<String, Integer> entry : fanInMap.entrySet()) {
                    System.out.println(entry.getKey() + ": " + entry.getValue());
                }

                // 输出扇出
                System.out.println("Fan-Out:");
                for (Map.Entry<String, Integer> entry : fanOutMap.entrySet()) {
                    System.out.println(entry.getKey() + ": " + entry.getValue());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static String readFileToString(String filePath) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
        }
        return stringBuilder.toString();
    }
    private static List<MethodInfo> parseMethods(String source) {
        List<MethodInfo> methods = new ArrayList<>();
        ASTParser parser = ASTParser.newParser(AST.JLS8); // 指定使用的 Java 版本
        parser.setSource(source.toCharArray());
        parser.setKind(ASTParser.K_COMPILATION_UNIT);
        CompilationUnit cu = (CompilationUnit) parser.createAST(null);

        cu.accept(new ASTVisitor() {
            @Override
            public boolean visit(MethodDeclaration methodDeclaration) {
                int startLine = cu.getLineNumber(methodDeclaration.getStartPosition());
                int endLine = cu.getLineNumber(methodDeclaration.getStartPosition() + methodDeclaration.getLength());
                String methodSource = source.substring(methodDeclaration.getStartPosition(), methodDeclaration.getStartPosition() + methodDeclaration.getLength());
                methods.add(new MethodInfo(methodDeclaration.getName().getIdentifier(), startLine, endLine, methodSource));
                return super.visit(methodDeclaration);
            }
        });

        return methods;
    }
    private static Set<String> findCalledMethods(MethodInfo method, List<MethodInfo> allMethods) {
        Set<String> calledMethods = new HashSet<>();
        ASTParser parser = ASTParser.newParser(AST.JLS8); // 使用Java 8的语法
        String codes = "public class Example {\n" + method.getSource() + "}\n";
        parser.setSource(codes.toCharArray());
        parser.setKind(ASTParser.K_COMPILATION_UNIT);
        CompilationUnit cu = (CompilationUnit) parser.createAST(null);

        cu.accept(new ASTVisitor() {
            @Override
            public boolean visit(MethodInvocation methodInvocation) {
                String methodName = methodInvocation.getName().getIdentifier();
                calledMethods.add(methodName);
                return super.visit(methodInvocation);
            }
        });

        // Remove self-call if exists
        calledMethods.remove(method.getName());

        return calledMethods;
    }
    static class MethodInfo {
        private String name;
        private int startLine;
        private int endLine;
        private String source;

        public MethodInfo(String name, int startLine, int endLine, String source) {
            this.name = name;
            this.startLine = startLine;
            this.endLine = endLine;
            this.source = source;
        }

        public String getName() {
            return name;
        }

        public int getStartLine() {
            return startLine;
        }

        public int getEndLine() {
            return endLine;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }
    }

}
