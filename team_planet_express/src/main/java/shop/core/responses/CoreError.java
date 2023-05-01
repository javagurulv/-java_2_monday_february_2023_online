package shop.core.responses;

public class CoreError {

    private final String field;
    private final String errorCode;
    private final String message;

    public CoreError(String field, String errorCode, String message) {
        this.field = field;
        this.errorCode = errorCode;
        this.message = message;
    }

    public String getField() {
        return field;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getMessage() {
        return message;
    }

}
