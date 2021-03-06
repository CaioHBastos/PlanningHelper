package com.bswork.helper.dataprovider.service.utils;

import com.bswork.helper.dataprovider.model.Task;
import com.opencsv.CSVWriter;

import java.io.File;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

        public File writeJiraImporter(List<Task> tasks) {

        try {
            File file = new File("src/main/resources/jiraImporter.txt");
            file.createNewFile();
            String[] headers = {"Issue Type", "Summary", "Description", "Hours\n(Planning)", "IssueID", "Parent ID/Jira Key", "Epic Link", "Complexity Points", "Priority", "Components", "Fix Versions", "Labels", "Due Date", "Team", "Original Estimate"};
            Writer writer = Files.newBufferedWriter(Paths.get(file.getPath()));
            CSVWriter csvWriter = new CSVWriter(writer);
            List<String[]> linhas = new ArrayList<>();

            for (Task task : tasks) {
                linhas.add(new String[]{task.getIssueType(), task.getSummary(), task.getDescription(), task.getHours().toString(), task.getIssueId().toString(), task.getStory().getStoryNumber(), task.getEpicLink(), task.getComplexityPoints(), task.getPriority(), task.getComponents(), task.getFixVersions(), task.getLabels(), task.getDueDate(), task.getTeam(), task.getOriginalEstimate().toString()});
            }

            csvWriter.writeNext(headers);
            csvWriter.writeAll(linhas);

            csvWriter.flush();
            writer.close();

            return file;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public File writePlanningPokerTxt(List<Task> tasks) {

        try {
            File file = new File("src/main/resources/planningpoker.txt");
            file.createNewFile();
            Writer writer = Files.newBufferedWriter(Paths.get(file.getPath()));
            CSVWriter csvWriter = new CSVWriter(writer);
            List<String[]> linhas = new ArrayList<>();

            for (Task task : tasks) {
                linhas.add(new String[]{task.getStory().getStoryNumber(), " = ", task.getSummary()});
            }

            csvWriter.writeAll(linhas);

            csvWriter.flush();
            writer.close();
            return file;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public File returnParametrizationFile() {
            return new File("src/main/resources/ArquivoParametrizacao.txt");
    }
}
