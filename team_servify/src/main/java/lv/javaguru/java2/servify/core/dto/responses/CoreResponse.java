package lv.javaguru.java2.servify.core.dto.responses;

import java.util.List;

public abstract class CoreResponse {
    private List<CoreError> errors;

    public CoreResponse() { }

    public CoreResponse(List<CoreError> errors) {
        this.errors = errors;
    }

    public List<CoreError> getErrors() {
        return errors;
    }

    public boolean hasErrors() {
        return errors != null && !errors.isEmpty();
    }
}
