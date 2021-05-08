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
public class KeyEvent implements Event {

    @Builder.Default
    private Type type = Type.KEY;
    private String locale;
    private List<Integer> keyCodes;

}
