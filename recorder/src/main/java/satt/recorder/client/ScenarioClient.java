package satt.recorder.client;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import satt.model.Scenario;

@Slf4j
@Component
public class ScenarioClient {

    public void saveScenario(Scenario scenario) {
        log.info("{}", scenario);
    }

}
