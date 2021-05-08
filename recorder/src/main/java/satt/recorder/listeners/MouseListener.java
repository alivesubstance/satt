package satt.recorder.listeners;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jnativehook.mouse.SwingMouseAdapter;
import org.springframework.stereotype.Component;
import satt.model.MouseClickEvent;
import satt.recorder.service.ScenarioService;
import satt.recorder.util.ModifiersUtil;

import java.awt.event.MouseEvent;

@Slf4j
@Component
@RequiredArgsConstructor
public class MouseListener extends SwingMouseAdapter {

    private final ScenarioService scenarioService;

    @Override
    public void mouseClicked(MouseEvent e) {
        scenarioService.addEvent(new MouseClickEvent(
                e.getX(),
                e.getY(),
                e.getButton(),
                ModifiersUtil.getSpecialKeyCodes(e.getModifiersEx())
        ));
    }

    @Override
    protected int getJavaModifiers(int nativeModifiers) {
        return ModifiersUtil.getJavaModifiers(nativeModifiers);
    }
}
