package com.bswork.helper.domain.gateway;

import org.springframework.core.io.InputStreamResource;

import java.io.IOException;
import java.util.Map;

public interface FileGateway {

    void createTeamName(Map<String, String> nameTime);
    String createFile(boolean exporterJiraImporter);
    InputStreamResource returnJiraImporterTxt() throws IOException;
    InputStreamResource downloadTextFile() throws IOException;
    InputStreamResource returnPlanningPokerTxt() throws IOException;
    InputStreamResource returnParametrizationFile() throws IOException;
    long getLengthFile();
    void deleteFile();
}
