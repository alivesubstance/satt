package satt.model;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.EXISTING_PROPERTY,
        property = "type",
        defaultImpl = Void.class,
        visible = true
)
//@JsonSubTypes({
//        @JsonSubTypes.Type(value = RemoveMoneyInputValidationRuleOperation.class, name = "REMOVE_MONEY_INPUT_MAX_VALUE_VALIDATION_RULE"),
//})
public interface Step {

    Type getType();

    enum Type {
        MOUSE,
        KEYBOARD
    }

}

