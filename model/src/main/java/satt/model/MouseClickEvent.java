package satt.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MouseClickEvent implements Event {

    private int x;
    private int y;
    private int button;
    private List<Integer> specialKeyCodes;

    @Override
    public Type getType() {
        return Type.MOUSE_CLICK;
    }
}
