package satt.model;

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
public class KeyEvent implements Event {

    private String locale;
    private String keyText;
    private List<Integer> keyCodes;

    @Override
    public Type getType() {
        return Type.KEYBOARD;
    }
}
