package satt.recorder.util;
import lombok.experimental.UtilityClass;
import org.jnativehook.NativeInputEvent;
import org.springframework.lang.NonNull;

import java.awt.event.KeyEvent;
import java.util.*;

public class ModifiersUtil {

    public static int getJavaModifiers(int nativeModifiers) {
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

    public static boolean isMacCommandPressed(int modifiers) {
        return (modifiers & KeyEvent.META_DOWN_MASK) != 0;
    }

    public static boolean isCtrPressed(int modifiers) {
        return (modifiers & KeyEvent.CTRL_DOWN_MASK) != 0;
    }

    // option at MAC
    public static boolean isAltPressed(int modifiers) {
        return (modifiers & KeyEvent.ALT_DOWN_MASK) != 0;
    }

    public static boolean isShiftPressed(int modifiers) {
        return (modifiers & KeyEvent.SHIFT_DOWN_MASK) != 0;
    }

    @NonNull
    public static List<Integer> getSpecialKeyCodes(int modifiers) {
        List<Integer> keyCodes = new ArrayList<>();
        if (isAltPressed(modifiers)) {
            keyCodes.add(KeyEvent.VK_ALT);
        }
        if (isCtrPressed(modifiers)) {
            keyCodes.add(KeyEvent.VK_CONTROL);
        }
        if (isShiftPressed(modifiers)) {
            keyCodes.add(KeyEvent.VK_SHIFT);
        }
        if (isMacCommandPressed(modifiers)) {
            keyCodes.add(KeyEvent.VK_META);
        }

        return keyCodes;
    }
}
