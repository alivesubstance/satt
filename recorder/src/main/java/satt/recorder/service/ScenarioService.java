package satt.recorder.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import satt.model.DelayEvent;
import satt.model.Event;
import satt.model.Scenario;
import satt.recorder.client.ScenarioClient;
import satt.recorder.property.RecorderProperties;

@Service
@RequiredArgsConstructor
public class ScenarioService {

    private RecorderProperties properties;
    private MetadataService metadataService;
    private ScenarioClient scenarioClient;

    private Scenario scenario;
    private long lastEventTimestamp;

    public void startScenario() {
        scenario = Scenario.builder()
                .metadata(metadataService.getMetadata())
                .build();
    }

    public void finishScenario() {
        scenarioClient.saveScenario(scenario);
        scenario = null;
    }

    public void addEvent(Event e) {
        if (scenario == null) {
            throw new IllegalStateException("Scenario hasn't started yet");
        }

        scenario.addEvent(e);

        long now = System.currentTimeMillis();
        scenario.addEvent(new DelayEvent(now - lastEventTimestamp));
        lastEventTimestamp = now;
    }

}
