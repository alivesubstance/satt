package satt.recorder.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.Unirest;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.MimeTypeUtils;
import satt.model.Scenario;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

@Slf4j
@Component
public class ScenarioClient {

    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper()
            .registerModule(new JavaTimeModule());

    public static final String SCENARIO_JSON_FOLDER = System.getProperty("user.home");

    @Value("${scenarioUrl}")
    private String scenarioUrl;

    @Value("${http.connectionTimeout}")
    private long connectionTimeout;

    @Value("${http.socketTimeout}")
    private long socketTimeout;

    public void saveScenario(Scenario scenario) {
        String scenarioJson = toJson(scenario);

        HttpResponse<String> response;
        String errMsg = "Failed to save scenario";
        try {
            log.debug("Save scenario {}", scenarioJson);

            Unirest.setTimeouts(connectionTimeout, socketTimeout);
            response = Unirest.post(scenarioUrl)
                    .header(HttpHeaders.CONTENT_TYPE, MimeTypeUtils.APPLICATION_JSON_VALUE + "; charset=utf-8")
                    .body(scenarioJson)
                    .asString();
        } catch (Exception e) {
            log.error("{}: {}", errMsg, scenarioJson, e);
            throw new RuntimeException(e);
        }

        int status = response.getStatus();
        if (status % 200 != 0) {
            log.error("Response text: {}, response body: {}. Scenario {}",
                    response.getStatusText(), response.getBody(), scenarioJson
            );
            throw new RuntimeException(errMsg);
        }
    }

    private String toJson(Scenario scenario) {
        String scenarioJson;
        try {
            scenarioJson = OBJECT_MAPPER.writeValueAsString(scenario);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return scenarioJson;
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
