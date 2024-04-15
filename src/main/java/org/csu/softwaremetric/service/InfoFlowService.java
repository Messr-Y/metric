package org.csu.softwaremetric.service;

import org.csu.softwaremetric.entity.InfoFlow;
import org.eclipse.jdt.core.dom.*;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

@Service
public class InfoFlowService {
    //
    public List<InfoFlow> getInfoFlows(String filename) {
        String filePath = filename; // 要解析的 Java 文件路径
        List<InfoFlow> methods = null;
        try {
            String source = readFileToString(filePath);
            if (source != null) {
                methods = parseMethods(source);
                Map<String, Integer> fanInMap = new HashMap<>();
                Map<String, Integer> fanOutMap = new HashMap<>();
                for (InfoFlow method : methods) {
                    fanInMap.put(method.getMethodName(), 0);
                    fanOutMap.put(method.getMethodName(), 0);
                }

                for (InfoFlow method : methods) {
                    Set<String> calledMethods = findCalledMethods(method, methods);
                    fanOutMap.put(method.getMethodName(), calledMethods.size());

                    for (String calledMethod : calledMethods) {
                        if (fanInMap.containsKey(calledMethod)) {
                            fanInMap.put(calledMethod, fanInMap.get(calledMethod) + 1);
                        }
                    }
                }
                for (InfoFlow method : methods) {
                    String methodName = method.getMethodName();
                    method.setFlowIn(fanInMap.get(methodName));
                    method.setFlowOut(fanOutMap.get(methodName));
                    method.updateData();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return methods;
    }

    private static Set<String> findCalledMethods(InfoFlow method, List<InfoFlow> allMethods) {
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
        calledMethods.remove(method.getMethodName());

        return calledMethods;
    }

    // 读取文件，将代码转为字符串
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

    // 根据代码获得所有函数名和函数范围
    private static List<InfoFlow> parseMethods(String source) {
        List<InfoFlow> methods = new ArrayList<>();
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
                methods.add(new InfoFlow(methodDeclaration.getName().getIdentifier(), startLine, endLine, methodSource));
                return super.visit(methodDeclaration);
            }
        });

        return methods;
    }
}
