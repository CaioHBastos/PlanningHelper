package com.bswork.helper.domain.usecase;

import com.bswork.helper.domain.exception.BusyException;
import com.bswork.helper.domain.exception.ValueInvalidException;
import com.bswork.helper.domain.gateway.FileGateway;
import lombok.AllArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

@AllArgsConstructor
@Service
public class FileJourneyUsecase {

    private final FileGateway fileGateway;

    public void createTeamName(Map<String, String> nameTime) {
        if (Objects.isNull(nameTime)) {
            throw new ValueInvalidException("O nome do time não foi preenchido.");
        }

        fileGateway.createTeamName(nameTime);
    }

    public InputStreamResource findByFile(boolean exportJiraImporter, boolean exportPlanningPoker, boolean exportParameterizationFile) throws IOException {

        if (exportJiraImporter) {
            return fileGateway.returnJiraImporterTxt();
        }

        if (exportPlanningPoker) {
            return fileGateway.returnPlanningPokerTxt();
        }

        if (exportParameterizationFile) {
            return fileGateway.returnParametrizationFile();
        }

        throw new BusyException("Não foi possível realizar o download do arquivo.");
    }

    public long getLengthFile() {
        return fileGateway.getLengthFile();
    }

    public String getFileName(boolean exportJiraImporter, boolean exportPlanningPoker, boolean exportParameterizationFile) {

        if (exportJiraImporter) {
            return fileGateway.createFile(true);
        }

        if (exportPlanningPoker) {
            return fileGateway.createFile(false);
        }

        if (exportParameterizationFile) {
            return "ArquivoParametrizacao";
        }

        throw new BusyException("Não foi possível realizar o download do arquivo.");
    }

    public void deleteFile() {
        fileGateway.deleteFile();
    }
}
