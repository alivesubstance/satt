package satt.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MouseEvent implements Event {
    @Override
    public Type getType() {
        return Type.MOUSE;
    }
}
