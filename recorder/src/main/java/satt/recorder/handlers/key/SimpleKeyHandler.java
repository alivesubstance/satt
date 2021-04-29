package satt.recorder.handlers.key;

import org.jnativehook.keyboard.NativeKeyEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import satt.recorder.service.ScenarioService;

@Component
public class SimpleKeyHandler implements KeyEventHandler {

    private ScenarioService scenarioService;

    @Autowired
    public SimpleKeyHandler(ScenarioService scenarioService) {
        this.scenarioService = scenarioService;
    }

    @Override
    public boolean process(NativeKeyEvent e) {


        return false;
    }

}
