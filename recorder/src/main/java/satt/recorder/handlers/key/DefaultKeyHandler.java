package satt.recorder.handlers.key;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import satt.recorder.service.KeyboardLayoutService;
import satt.recorder.service.ScenarioService;

import java.awt.event.KeyEvent;
import java.util.*;

import static satt.recorder.util.ModifiersUtil.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class DefaultKeyHandler implements KeyEventHandler {

    private final ScenarioService scenarioService;
    private final KeyboardLayoutService keyboardLayoutService;

    @Override
    public boolean process(KeyEvent e) {
        satt.model.KeyEvent keyEvent = satt.model.KeyEvent.builder()
                .keyCodes(createKeyCodes(e))
                .locale(keyboardLayoutService.getKeyboardLocale())
                .build();

        scenarioService.addEvent(keyEvent);

        return true;
    }

    private List<Integer> createKeyCodes(KeyEvent e) {
        List<Integer> keys = getSpecialKeyCodes(e.getModifiersEx());

        if (!isSpecialKey(e)) {
            keys.add(e.getKeyCode());
        }
        return keys;
    }

}
