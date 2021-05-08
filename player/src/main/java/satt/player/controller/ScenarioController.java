package satt.player.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import satt.model.Scenario;
import satt.player.service.ScenarioService;

@RestController
@RequiredArgsConstructor
public class ScenarioController {

    private final ScenarioService scenarioService;

    @Async
    @PostMapping("/scenario")
    public void startScenario(@RequestBody Scenario scenario) {
        scenarioService.startScenario(scenario);
    }

}

