package satt.recorder.listeners;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jnativehook.mouse.SwingMouseWheelAdapter;
import org.springframework.stereotype.Component;
import satt.model.MouseClickEvent;
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
        scenarioService.addEvent(new satt.model.MouseWheelEvent(

        ));
    }

    @Override
    protected int getJavaModifiers(int nativeModifiers) {
        return ModifiersUtil.getJavaModifiers(nativeModifiers);
    }
}
