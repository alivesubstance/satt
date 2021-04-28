package satt.model;

public class MouseEvent implements Event {
    @Override
    public Type getType() {
        return Type.MOUSE;
    }
}
