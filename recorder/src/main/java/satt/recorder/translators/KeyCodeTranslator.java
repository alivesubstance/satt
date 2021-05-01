package satt.recorder.translators;

import org.jnativehook.keyboard.NativeKeyEvent;
import satt.model.OsType;

import java.awt.event.KeyEvent;
import java.util.*;

public abstract class KeyCodeTranslator {

    // JNativeHook to Java AWT Robot key codes mapping
    private static final Map<Integer, Integer> KEY_CODES = new HashMap<>();

    public KeyCodeTranslator(Map<Integer, Integer> osSpecifiKeyCodes) {
        KEY_CODES.put(NativeKeyEvent.VC_A, KeyEvent.VK_A);
        KEY_CODES.put(NativeKeyEvent.VC_B, KeyEvent.VK_B);
        KEY_CODES.put(NativeKeyEvent.VC_C, KeyEvent.VK_C);
        KEY_CODES.put(NativeKeyEvent.VC_D, KeyEvent.VK_D);
        KEY_CODES.put(NativeKeyEvent.VC_E, KeyEvent.VK_E);
        KEY_CODES.put(NativeKeyEvent.VC_F, KeyEvent.VK_F);
        KEY_CODES.put(NativeKeyEvent.VC_G, KeyEvent.VK_G);
        KEY_CODES.put(NativeKeyEvent.VC_H, KeyEvent.VK_H);
        KEY_CODES.put(NativeKeyEvent.VC_I, KeyEvent.VK_I);
        KEY_CODES.put(NativeKeyEvent.VC_J, KeyEvent.VK_J);
        KEY_CODES.put(NativeKeyEvent.VC_K, KeyEvent.VK_K);
        KEY_CODES.put(NativeKeyEvent.VC_L, KeyEvent.VK_L);
        KEY_CODES.put(NativeKeyEvent.VC_M, KeyEvent.VK_M);
        KEY_CODES.put(NativeKeyEvent.VC_N, KeyEvent.VK_N);
        KEY_CODES.put(NativeKeyEvent.VC_O, KeyEvent.VK_O);
        KEY_CODES.put(NativeKeyEvent.VC_P, KeyEvent.VK_P);
        KEY_CODES.put(NativeKeyEvent.VC_Q, KeyEvent.VK_Q);
        KEY_CODES.put(NativeKeyEvent.VC_R, KeyEvent.VK_R);
        KEY_CODES.put(NativeKeyEvent.VC_S, KeyEvent.VK_S);
        KEY_CODES.put(NativeKeyEvent.VC_T, KeyEvent.VK_T);
        KEY_CODES.put(NativeKeyEvent.VC_U, KeyEvent.VK_U);
        KEY_CODES.put(NativeKeyEvent.VC_V, KeyEvent.VK_V);
        KEY_CODES.put(NativeKeyEvent.VC_W, KeyEvent.VK_W);
        KEY_CODES.put(NativeKeyEvent.VC_X, KeyEvent.VK_X);
        KEY_CODES.put(NativeKeyEvent.VC_Y, KeyEvent.VK_Y);
        KEY_CODES.put(NativeKeyEvent.VC_Z, KeyEvent.VK_Z);

        KEY_CODES.put(NativeKeyEvent.VC_F1, KeyEvent.VK_F1);
//        public static final int VC_F1 = 59;
//        public static final int VC_F2 = 60;
//        public static final int VC_F3 = 61;
//        public static final int VC_F4 = 62;
//        public static final int VC_F5 = 63;
//        public static final int VC_F6 = 64;
//        public static final int VC_F7 = 65;
//        public static final int VC_F8 = 66;
//        public static final int VC_F9 = 67;
//        public static final int VC_F10 = 68;
//        public static final int VC_F11 = 87;
//        public static final int VC_F12 = 88;
//        public static final int VC_F13 = 91;
//        public static final int VC_F14 = 92;
//
//        public static final int VC_1 = 2;
//        public static final int VC_2 = 3;
//        public static final int VC_3 = 4;
//        public static final int VC_4 = 5;
//        public static final int VC_5 = 6;
//        public static final int VC_6 = 7;
//        public static final int VC_7 = 8;
//        public static final int VC_8 = 9;
//        public static final int VC_9 = 10;
//        public static final int VC_0 = 11;
//        public static final int VC_MINUS = 12;
//        public static final int VC_EQUALS = 13;
//        public static final int VC_BACKSPACE = 14;
//        public static final int VC_TAB = 15;
//        public static final int VC_CAPS_LOCK = 58;

        KEY_CODES.put(NativeKeyEvent.VC_SHIFT, KeyEvent.VK_SHIFT); //shift

        KEY_CODES.put(NativeKeyEvent.VC_CONTROL, KeyEvent.VK_CONTROL); //ctrl
        KEY_CODES.put(NativeKeyEvent.VC_ALT, KeyEvent.VK_ALT); //option
        KEY_CODES.put(NativeKeyEvent.VC_META, KeyEvent.VK_META); //cmd

        KEY_CODES.putAll(osSpecifiKeyCodes);
    }

    public Integer toRobotKeyCode(int jNativeHookKeyCode) {
        return KEY_CODES.get(jNativeHookKeyCode);
    }

    public abstract OsType getOs();

}
