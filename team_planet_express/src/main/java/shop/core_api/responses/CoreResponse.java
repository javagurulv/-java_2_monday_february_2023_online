package shop.core_api.responses;

import lombok.Getter;

import java.util.List;

@Getter
public abstract class CoreResponse {

    private List<CoreError> errors;

    public CoreResponse() {
    }

    public CoreResponse(List<CoreError> errors) {
        this.errors = errors;
    }

    public boolean hasErrors() {
        return errors != null && !errors.isEmpty();
    }

}
