package satt.recorder.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import satt.model.Scenario;
import satt.recorder.property.RecorderProperties;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

@Slf4j
@Component
@RequiredArgsConstructor
public class ScenarioClient {

    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper()
            .registerModule(new JavaTimeModule());

    public static final String SCENARIO_JSON_FOLDER = "/Users/mirian/code/3rdparty/satt/scenarios";

    private RecorderProperties properties;

    public void saveScenario(Scenario scenario) {
        String scenarioJson;
        try {
            scenarioJson = OBJECT_MAPPER.writeValueAsString(scenario);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

        HttpResponse<String> response;
        String errMsg = "Failed to save scenario";
        try {
            Unirest.setTimeouts(500, 3000);
            response = Unirest.post(properties.scenarioUrl)
                    .body(scenarioJson)
                    .asString();
        } catch (Exception e) {
            log.error("{}: {}", errMsg, scenarioJson);
            throw new RuntimeException(e);
        }

        int status = response.getStatus();
        if (status % 200 != 0) {
            log.error("{}: {}", errMsg, scenarioJson);
            throw new RuntimeException(errMsg);
        }
    }

    public void saveScenarioToFile(Scenario scenario) {
        try {
            String scenarioJson = OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(scenario);
            File scenarioFile = File.createTempFile("scenario", ".json", new File(SCENARIO_JSON_FOLDER));
            Files.writeString(scenarioFile.toPath(), scenarioJson, StandardCharsets.UTF_8);

            log.info("Scenario json saved to {}", scenarioFile);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
