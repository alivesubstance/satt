package satt.recorder.client;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JSR310Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import satt.model.Scenario;

@Slf4j
@Component
public class ScenarioClient {

    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper()
            .registerModule(new JavaTimeModule());

    public void saveScenario(Scenario scenario) {

        try {
            String scenarioStr = OBJECT_MAPPER.writerWithDefaultPrettyPrinter().writeValueAsString(scenario);
            System.out.println(scenarioStr);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}
