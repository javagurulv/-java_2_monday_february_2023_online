package java2.eln.core.requests;

public class DeleteReactionByCodeRequest {
    private final String code;

    public DeleteReactionByCodeRequest(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
