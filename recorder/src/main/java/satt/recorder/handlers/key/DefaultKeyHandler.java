package satt.recorder.handlers.key;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.springframework.stereotype.Component;
import satt.recorder.service.KeyboardLayoutService;
import satt.recorder.service.ScenarioService;

import java.awt.event.KeyEvent;
import java.util.*;

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
        List<Integer> keys = new ArrayList<>();
        if (isAltPressed(e)) {
            keys.add(KeyEvent.VK_ALT);
        }
        if (isCtrPressed(e)) {
            keys.add(KeyEvent.VK_CONTROL);
        }
        if (isShiftPressed(e)) {
            keys.add(KeyEvent.VK_SHIFT);
        }
        if (isMacCommandPressed(e)) {
            keys.add(KeyEvent.VK_META);
        }

        if (!isSpecialKey(e)) {
            keys.add(e.getKeyCode());
        }
        return keys;
    }

}
