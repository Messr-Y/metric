package org.csu.softwaremetric.service;

import ch.qos.logback.core.net.SyslogOutputStream;
import org.csu.softwaremetric.entity.NameWeight;
import org.csu.softwaremetric.entity.UseCase;
import org.csu.softwaremetric.entity.UseCaseDetail;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class UseCaseService {
    public UseCase getActorAndUseCaseCount(String filename) {
        UseCase useCase = new UseCase();
        int actorCount = 0;
        int useCaseCount = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            // 逐行读取文件内容
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("<o:Actor Id")) {
                    actorCount += 1;
                }
                if (line.contains("<o:UseCase Id")) {
                    useCaseCount += 1;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        useCase.setUseCaseCount(useCaseCount);
        useCase.setActorCount(actorCount);
        return useCase;
    }

    public UseCaseDetail getActorAndUseCaseDetail(String filename) {
        UseCaseDetail useCaseDetail = new UseCaseDetail();
        ArrayList<NameWeight> actorList = new ArrayList<>();
        ArrayList<NameWeight> useCaseList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            // 逐行读取文件内容
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains("<o:Actor Id")) {
                    reader.readLine();
                    line = reader.readLine();
                    Pattern pattern = Pattern.compile("<a:Name>(.*?)</a:Name>");
                    Matcher matcher = pattern.matcher(line);
                    if (matcher.find()) {
                        String actor = matcher.group(1);
                        actorList.add(new NameWeight(actor, 0));
                    }
                }
                if (line.contains("<o:UseCase Id")) {
                    reader.readLine();
                    line = reader.readLine();
                    Pattern pattern = Pattern.compile("<a:Name>(.*?)</a:Name>");
                    Matcher matcher = pattern.matcher(line);
                    if (matcher.find()) {
                        String useCase = matcher.group(1);
                        useCaseList.add(new NameWeight(useCase, 0));
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        useCaseDetail.setUseCaseList(useCaseList);
        useCaseDetail.setActorList(actorList);
        return useCaseDetail;
    }
}