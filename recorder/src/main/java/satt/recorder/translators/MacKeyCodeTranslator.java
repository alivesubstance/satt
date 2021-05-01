package satt.recorder.translators;

import org.jnativehook.keyboard.NativeKeyEvent;
import org.springframework.stereotype.Component;
import satt.model.OsType;

import java.awt.event.KeyEvent;
import java.util.*;

@Component
public class MacKeyCodeTranslator extends KeyCodeTranslator {

    public MacKeyCodeTranslator() {
        super(createKeyCodesMapping());
    }

    private static Map<Integer, Integer> createKeyCodesMapping() {
        Map<Integer, Integer> keyCodes = new HashMap<>();
        keyCodes.put(3638, KeyEvent.VK_SHIFT); //right shift on mac book
        return keyCodes;
    }

    @Override
    public OsType getOs() {
        return OsType.MAC;
    }

}
