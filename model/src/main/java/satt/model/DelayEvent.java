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

    private long millis;

    @Override
    public Type getType() {
        return Type.DELAY;
    }
}
