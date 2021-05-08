package satt.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString(exclude = "events")
public class Scenario {

    private static final String DATE_TIME_FORMAT = "yyyy-MM-dd hh:mm:ss";

    @Builder.Default
    private UUID id = UUID.randomUUID();
    private Metadata metadata;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_TIME_FORMAT)
    private Date startDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DATE_TIME_FORMAT)
    private Date endDate;

    @Builder.Default
    private List<Event> events = new ArrayList<>();

    public void addEvent(Event e) {
        events.add(e);
    }

}
