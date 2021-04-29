package satt.model;

public class DelayEvent implements Event {

    private long millis;

    public DelayEvent(long millis) {
        this.millis = millis;
    }

    @Override
    public Type getType() {
        return Type.DELAY;
    }
}
