package satt.recorder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import satt.model.DelayEvent;
import satt.model.Event;
import satt.model.Scenario;
import satt.recorder.property.RecorderProperties;

@Service
public class ScenarioService {

    private final RecorderProperties properties;
    private final MetadataService metadataService;

    private Scenario scenario;
    private long lastEventTimestamp;

    @Autowired
    public ScenarioService(RecorderProperties properties, MetadataService metadataService) {
        this.properties = properties;
        this.metadataService = metadataService;
    }

    public void startScenario() {
        scenario = new Scenario(metadataService.getMetadata());
    }

    public void finishScenario() {

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
