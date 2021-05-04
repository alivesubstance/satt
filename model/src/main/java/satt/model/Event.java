package satt.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.awt.event.MouseWheelEvent;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "type",
        defaultImpl = Void.class,
        visible = true
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = KeyEvent.class, name = "KEY"),
        @JsonSubTypes.Type(value = MouseClickEvent.class, name = "MOUSE_CLICK"),
        @JsonSubTypes.Type(value = MouseWheelEvent.class, name = "MOUSE_WHEEL"),
        @JsonSubTypes.Type(value = DelayEvent.class, name = "DELAY")
})
public interface Event {

    Type getType();

    enum Type {
        KEY,
        MOUSE_CLICK,
        MOUSE_WHEEL,
        DELAY
    }

}

