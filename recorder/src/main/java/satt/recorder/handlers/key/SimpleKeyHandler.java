package satt.recorder.handlers.key;

import lombok.RequiredArgsConstructor;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.springframework.stereotype.Component;
import satt.model.KeyEvent;
import satt.recorder.service.KeyboardLayoutService;
import satt.recorder.service.ScenarioService;

import java.util.*;

//@Component
@RequiredArgsConstructor
public class SimpleKeyHandler implements KeyEventHandler {

    private final ScenarioService scenarioService;
    private final KeyboardLayoutService keyboardLayoutService;

    @Override
    public boolean process(NativeKeyEvent e) {
        KeyEvent keyEvent = KeyEvent.builder()
                .keyCodes(Collections.singletonList(e.getKeyCode()))
                .locale(keyboardLayoutService.getKeyboardLocale())
                .keyText(NativeKeyEvent.getKeyText(e.getKeyCode()))
                .build();

        scenarioService.addEvent(keyEvent);

        return true;
    }

}
