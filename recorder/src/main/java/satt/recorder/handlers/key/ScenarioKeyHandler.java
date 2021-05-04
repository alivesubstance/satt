package satt.recorder.handlers.key;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import satt.recorder.service.ScenarioService;

import java.awt.event.KeyEvent;

import static satt.recorder.util.ModifiersUtil.*;

@Slf4j
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
@RequiredArgsConstructor
public class ScenarioKeyHandler implements KeyEventHandler {

    private final ScenarioService scenarioService;

    @Override
    public boolean process(KeyEvent e) {
        int modifiers = e.getModifiersEx();
        if (isCtrPressed(modifiers) &&
                isShiftPressed(modifiers) &&
                (isAltPressed(modifiers)) || isMacCommandPressed(modifiers)
        ) {
            if (e.getKeyCode() == NativeKeyEvent.VC_F9) {
                scenarioService.startScenario();
                return true;
            } else if (e.getKeyCode() == NativeKeyEvent.VC_F10) {
                scenarioService.finishScenario();
                return true;
            }
        }

        return false;
    }

}
