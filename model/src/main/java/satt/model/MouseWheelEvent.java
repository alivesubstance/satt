package satt.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MouseWheelEvent implements Event {

    private int amount;

    @Override
    public Type getType() {
        return Type.MOUSE_WHEEL;
    }
}
