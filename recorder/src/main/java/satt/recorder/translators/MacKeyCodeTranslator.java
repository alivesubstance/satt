package satt.recorder.translators;

import org.jnativehook.keyboard.NativeKeyEvent;

import java.awt.event.KeyEvent;
import java.util.*;

public class MacKeyCodeTranslator {

    // JNativeHook to Java AWT Robot mapping
    private static final Map<Integer, Integer> KEY_CODES = new HashMap<>();

    public MacKeyCodeTranslator() {
        KEY_CODES.put(NativeKeyEvent.VC_A, KeyEvent.VK_A);
        KEY_CODES.put(NativeKeyEvent.VC_B, KeyEvent.VK_B);
        KEY_CODES.put(NativeKeyEvent.VC_C, KeyEvent.VK_C);
    }

}
