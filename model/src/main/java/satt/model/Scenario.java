package satt.model;

import java.util.*;

public class Scenario {

    private Metadata metadata;
    private List<Event> events = new ArrayList<>();

    public Scenario(Metadata metadata) {
        this.metadata = metadata;
    }

    public void addEvent(Event e) {
        events.add(e);
    }

    public Metadata getMetadata() {
        return metadata;
    }

    public List<Event> getEvents() {
        return events;
    }

}
