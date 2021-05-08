package satt.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DelayEvent implements Event {

    @Builder.Default
    private Type type = Type.DELAY;
    private long millis;
}
