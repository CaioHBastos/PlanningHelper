package com.bswork.helper.api.controller;

import com.bswork.helper.api.constants.UriConstants;
import com.bswork.helper.domain.usecase.FileJourneyUsecase;
import lombok.AllArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200", maxAge = 3600)
@AllArgsConstructor
@RestController
@RequestMapping(UriConstants.URI_BASE_FILE)
public class FileController {

    private final FileJourneyUsecase fileJourneyService;

    @PostMapping
    public ResponseEntity<?> create(Map<String, String> nomeTime) {
        fileJourneyService.createTeamName(nomeTime);
        return ResponseEntity.status(HttpStatus.CREATED).body(nomeTime);
    }

    @GetMapping
    public ResponseEntity<?> FindByFile(@RequestParam(required = false) boolean exportJiraImporter,
                                        @RequestParam(required = false) boolean exportPlanningPoker,
                                        @RequestParam(required = false) boolean exportParameterizationFile) throws IOException {

        String fileName = fileJourneyService.getFileName(exportJiraImporter, exportPlanningPoker, exportParameterizationFile);
        InputStreamResource inputStreamResource = fileJourneyService.findByFile(exportJiraImporter, exportPlanningPoker,
                exportParameterizationFile);
        try {
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + fileName)
                    .contentType(MediaType.TEXT_PLAIN)
                    .contentLength(fileJourneyService.getLengthFile())
                    .body(inputStreamResource);
        } finally {
            fileJourneyService.deleteFile();
        }
    }
}
