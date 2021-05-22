package satt.recorder.listeners;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jnativehook.mouse.SwingMouseWheelAdapter;
import org.springframework.stereotype.Component;
import satt.recorder.service.ScenarioService;
import satt.recorder.util.ModifiersUtil;

import java.awt.event.MouseWheelEvent;

@Slf4j
@Component
@RequiredArgsConstructor
public class MouseWheelListener extends SwingMouseWheelAdapter {

    private final ScenarioService scenarioService;

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        log.trace("Mouse wheel event unitsToScroll {}, scrollType {}, scrollAmount {}, " +
                        "wheelRotation {}, preciseWheelRotation {}",
                e.getUnitsToScroll(), e.getScrollType(), e.getScrollAmount(), e.getWheelRotation(),
                e.getPreciseWheelRotation()
        );

        scenarioService.addEvent(satt.model.MouseWheelEvent.builder()
                .amount(e.getWheelRotation())
                .specialKeyCodes(ModifiersUtil.getSpecialKeyCodes(e.getModifiersEx()))
                .build()
        );
    }

    @Override
    protected int getJavaModifiers(int nativeModifiers) {
        return ModifiersUtil.getJavaModifiers(nativeModifiers);
    }
}
