package satt.model;

import java.util.*;

public class KeyEvent implements Event {

    private final String description;
    private final List<Integer> keyCodes;

    public KeyEvent(String description, List<Integer> keyCodes) {
        this.description = description;
        this.keyCodes = keyCodes;
    }

    public String getDescription() {
        return description;
    }

    public List<Integer> getKeyCodes() {
        return keyCodes;
    }

    @Override
    public Type getType() {
        return Type.KEYBOARD;
    }
}
