package satt.recorder.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import satt.model.DelayEvent;
import satt.model.Event;
import satt.model.Scenario;
import satt.recorder.client.ScenarioClient;
import satt.recorder.property.RecorderProperties;

import java.time.LocalDateTime;

@Slf4j
@Service
@RequiredArgsConstructor
public class ScenarioService {

    private final MetadataService metadataService;
    private final ScenarioClient scenarioClient;

    private Scenario scenario;
    private long lastEventTimestamp;

    public void startScenario() {
        scenario = Scenario.builder()
                .metadata(metadataService.getMetadata())
                .startDate(LocalDateTime.now())
                .build();

        log.info("Start new scenario {}", scenario);
    }

    public void finishScenario() {
        if (scenario == null) {
            // scenario wasn't started. nothing to persist
            return;
        }

        log.info("Finish scenario {}", scenario);
        scenario.setEndDate(LocalDateTime.now());
        scenarioClient.saveScenario(scenario);

        // eliminate current scenario
        scenario = null;
    }

    public void addEvent(Event e) {
        if (scenario == null) {
            // scenario is not started yet. ignore event
            return;
        }

        log.debug("Add new event {}", e);
        scenario.addEvent(e);

        addDelayEvent();
    }

    private void addDelayEvent() {
        long now = System.currentTimeMillis();
        DelayEvent delayEvent = new DelayEvent(now - lastEventTimestamp);
        log.debug("Add delay event {}", delayEvent);

        scenario.addEvent(delayEvent);
        lastEventTimestamp = now;
    }

}
