package satt.recorder.handlers.key;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import satt.model.OsType;
import satt.recorder.handlers.key.KeyEventHandler;
import satt.recorder.service.ScenarioService;

@Slf4j
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
@RequiredArgsConstructor
public class ScenarioKeyHandler implements KeyEventHandler {

    private final ScenarioService scenarioService;

    @Override
    public OsType getOsType() {
        return OsType.MAC;
    }

    @Override
    public boolean process(NativeKeyEvent e) {
        if (isCtrPressed(e) && isShiftPressed(e) && (isAltPressed(e) || isMacCommandPressed(e))) {
            if (e.getKeyCode() == NativeKeyEvent.VC_F9) {
                scenarioService.startScenario();
            } else if (e.getKeyCode() == NativeKeyEvent.VC_F10) {
                scenarioService.finishScenario();
            }
            return true;
        }

        return false;
    }

}
