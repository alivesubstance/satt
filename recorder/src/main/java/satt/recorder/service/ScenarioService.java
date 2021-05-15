package satt.recorder.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import satt.model.DelayEvent;
import satt.model.Event;
import satt.model.Scenario;
import satt.recorder.client.ScenarioClient;

import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class ScenarioService {

    private final MetadataService metadataService;
    private final ScenarioClient scenarioClient;

    private Scenario scenario;
    private long lastEventTimestamp = -1;

    public void startScenario() {
        scenario = Scenario.builder()
                .metadata(metadataService.getMetadata())
                .startDate(new Date())
                .build();

        log.info("Start new scenario {}", scenario);
    }

    public void finishScenario() {
        if (scenario == null) {
            log.warn("Scenario has not been started yet");
            return;
        }

        log.info("Finish scenario {}", scenario);
        scenario.setEndDate(new Date());
        scenarioClient.saveScenario(scenario);

        // eliminate current scenario
        scenario = null;
    }

    public void addEvent(Event e) {
        if (scenario == null) {
            // scenario is not started yet. ignore event
            return;
        }
        addDelayEvent();

        log.debug("Add new event {}", e);
        scenario.addEvent(e);
    }

    private void addDelayEvent() {
        // do not add delay for first event
        if (lastEventTimestamp == -1) {
            lastEventTimestamp = System.currentTimeMillis();
            return;
        }

        long now = System.currentTimeMillis();
        DelayEvent delayEvent = DelayEvent.builder().millis(now - lastEventTimestamp).build();
        log.debug("Add delay event {}", delayEvent);

        scenario.addEvent(delayEvent);
        lastEventTimestamp = now;
    }

}
