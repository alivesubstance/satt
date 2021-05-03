package satt.recorder.listeners;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jnativehook.AbstractSwingInputAdapter;
import org.jnativehook.NativeInputEvent;
import org.jnativehook.keyboard.NativeKeyAdapter;
import org.jnativehook.keyboard.NativeKeyEvent;
import org.jnativehook.keyboard.SwingKeyAdapter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import satt.model.OsType;
import satt.recorder.handlers.key.KeyEventHandler;
import satt.recorder.service.MetadataService;

import javax.annotation.PostConstruct;
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
        int modifiers = 0x00;
        if ((nativeModifiers & NativeInputEvent.SHIFT_MASK) != 0) {
            modifiers |= KeyEvent.SHIFT_MASK;
            modifiers |= KeyEvent.SHIFT_DOWN_MASK;
        }
        if ((nativeModifiers & NativeInputEvent.META_MASK) != 0) {
            modifiers |= KeyEvent.META_MASK;
            modifiers |= KeyEvent.META_DOWN_MASK;
        }
        // the fix is only for Control. looks like copy paste issue in JNativeHook library
        // see AbstractSwingInputAdapter.java:42
        if ((nativeModifiers & NativeInputEvent.CTRL_MASK) != 0) {
            modifiers |= KeyEvent.CTRL_MASK;
            modifiers |= KeyEvent.CTRL_DOWN_MASK;
        }
        if ((nativeModifiers & NativeInputEvent.ALT_MASK) != 0) {
            modifiers |= KeyEvent.ALT_MASK;
            modifiers |= KeyEvent.ALT_DOWN_MASK;
        }
        if ((nativeModifiers & NativeInputEvent.BUTTON1_MASK) != 0) {
            modifiers |= KeyEvent.BUTTON1_MASK;
            modifiers |= KeyEvent.BUTTON1_DOWN_MASK;
        }
        if ((nativeModifiers & NativeInputEvent.BUTTON2_MASK) != 0) {
            modifiers |= KeyEvent.BUTTON2_MASK;
            modifiers |= KeyEvent.BUTTON2_DOWN_MASK;
        }
        if ((nativeModifiers & NativeInputEvent.BUTTON3_MASK) != 0) {
            modifiers |= KeyEvent.BUTTON3_MASK;
            modifiers |= KeyEvent.BUTTON3_DOWN_MASK;
        }

        return modifiers;
    }
}
