package satt.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MouseWheelEvent implements Event {

    private int amount;
    private List<Integer> specialKeyCodes;

    @Override
    public Type getType() {
        return Type.MOUSE_WHEEL;
    }
}
