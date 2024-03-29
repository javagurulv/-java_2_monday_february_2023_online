package lv.javaguru.java2.servify.core.dto.responses;

import lv.javaguru.java2.servify.core.domain.FieldTitle;

public class CoreError {

    private FieldTitle field;
    private String message;

    public CoreError(FieldTitle field, String message) {
        this.field = field;
        this.message = message;
    }

    public FieldTitle getField() {
        return field;
    }

    public String getMessage() {
        return message;
    }
}
