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

    @Builder.Default
    private Type type = Type.MOUSE_CLICK;
    private int x;
    private int y;
    private int button;
    private List<Integer> specialKeyCodes;
}
