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
public class MouseWheelEvent implements Event {

    @Builder.Default
    private Type type = Type.MOUSE_WHEEL;
    private int amount;
    private List<Integer> specialKeyCodes;
}
