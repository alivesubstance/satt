package satt.recorder.listeners;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.SwingKeyAdapter;
import org.springframework.stereotype.Component;
import satt.model.OsType;
import satt.recorder.handlers.key.KeyEventHandler;
import satt.recorder.service.MetadataService;
import satt.recorder.util.ModifiersUtil;

import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.*;

@Slf4j
@Component
@RequiredArgsConstructor
public class KeyListener extends SwingKeyAdapter {

    private static final int VC_SHIFT_RIGHT = 3638;

    private final MetadataService metadataService;
    private final List<KeyEventHandler> keyEventHandlers;

    @Override
    public void nativeKeyPressed(NativeKeyEvent nativeEvent) {
        KeyEvent javaKeyEvent = getJavaKeyEvent(nativeEvent);
        if (nativeEvent.getKeyCode() == VC_SHIFT_RIGHT) {
            javaKeyEvent = new KeyEvent(
                    this,
                    nativeEvent.getID() - (NativeKeyEvent.NATIVE_KEY_FIRST - KeyEvent.KEY_FIRST),
                    System.currentTimeMillis(),
                    getJavaModifiers(nativeEvent.getModifiers()),
                    KeyEvent.VK_SHIFT,
                    nativeEvent.getKeyChar(),
                    KeyEvent.KEY_LOCATION_RIGHT
            );
        }
        keyPressed(javaKeyEvent);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        OsType os = metadataService.getOs();
        log.trace("keyCode: {}, char: {}, modifiers: {}, modifiersText: {}, os: {}",
                e.getKeyCode(), e.getModifiersEx(), e.getKeyChar(), InputEvent.getModifiersExText(e.getModifiersEx()), os
        );

        if (e.getKeyCode() == KeyEvent.VK_UNDEFINED) {
            log.warn("Failed to get key code for event {}+", e);
        }

        for (KeyEventHandler keyEventHandler : keyEventHandlers) {
            if (keyEventHandler.getOsType() != null && keyEventHandler.getOsType() != os) {
                continue;
            }

            if (keyEventHandler.process(e)) {
                break;
            }
        }
    }

    @Override
    protected int getJavaModifiers(int nativeModifiers) {
        return ModifiersUtil.getJavaModifiers(nativeModifiers);
    }
}
