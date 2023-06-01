package java2.eln.core.requests;

public class DeleteReactionByIdRequest {
    private final Integer id;

    public DeleteReactionByIdRequest(String id) {
        this.id = Integer.valueOf(id);
    }

    public Integer getID() {
        return id;
    }
}
