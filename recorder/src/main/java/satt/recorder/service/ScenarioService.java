package satt.recorder.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import satt.model.Scenario;
import satt.recorder.property.RecorderProperties;

@Service
public class ScenarioService {

    private RecorderProperties properties;

    private Scenario scenario;
    private long lastEventTimestamp;

    @Autowired
    public ScenarioService(RecorderProperties properties) {
        this.properties = properties;
    }

    public void startScenario() {

    }

    public void finishScenario() {

    }

    public void addKeyEvent() {

    }

    public void addMouseEvent() {

    }

}
