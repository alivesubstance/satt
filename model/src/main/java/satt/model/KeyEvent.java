package satt.model;

public class KeyEvent implements Event {
    @Override
    public Type getType() {
        return Type.KEYBOARD;
    }
}
