package satt.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "events")
public class Scenario {

    @Builder.Default
    private UUID id = UUID.randomUUID();
    private Metadata metadata;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    @Builder.Default
    private List<Event> events = new ArrayList<>();

    public void addEvent(Event e) {
        events.add(e);
    }

}
