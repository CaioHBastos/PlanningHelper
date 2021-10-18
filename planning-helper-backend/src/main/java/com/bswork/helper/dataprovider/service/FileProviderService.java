package com.bswork.helper.dataprovider.service;

import com.bswork.helper.dataprovider.model.Task;
import com.bswork.helper.dataprovider.repository.TaskRepository;
import com.bswork.helper.dataprovider.service.utils.FileUtils;
import com.bswork.helper.domain.gateway.FileGateway;
import lombok.AllArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@AllArgsConstructor
@Component
public class FileProviderService implements FileGateway {

    private static String teamName;
    public static File file;
    private final TaskRepository taskRepository;

    @Override
    public void createTeamName(Map<String, String> nameTime) {
        teamName = nameTime.get("nomeTime");
    }

    @Override
    public String createFile(boolean exporterJiraImporter) {
        String fileName;
        if (exporterJiraImporter) {
            file = createJiraImporter();
            fileName = "jiraImporter.txt";
        } else {
            file = createPlanningPoker();
            fileName = "planningPoker.txt";
        }
        return fileName;
    }

    @Override
    public InputStreamResource returnJiraImporterTxt() throws IOException {
        return downloadTextFile();
    }

    @Override
    public InputStreamResource downloadTextFile() throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        InputStreamResource inputStreamResource = new InputStreamResource(fileInputStream);

        return inputStreamResource;
    }

    @Override
    public InputStreamResource returnPlanningPokerTxt() throws IOException {
        return downloadTextFile();
    }

    @Override
    public InputStreamResource returnParametrizationFile() throws IOException {
        file = new FileUtils().returnParametrizationFile();
        return downloadTextFile();
    }

    @Override
    public long getLengthFile() {
        return file.length();
    }

    @Override
    public void deleteFile() {
        file.delete();
    }

    public File createJiraImporter() {
        int contador = 1;
        List<Task> list = taskRepository.findAll();
        for (Task task : list) {
            task.setTeam(teamName);
            task.setIssueId(contador++);
            task.setOriginalEstimate(task.getHours() * 3600);
        }
        return new FileUtils().writeJiraImporter(list);
    }

    public File createPlanningPoker() {
        return new FileUtils().writePlanningPokerTxt(taskRepository.findAll());
    }
}
