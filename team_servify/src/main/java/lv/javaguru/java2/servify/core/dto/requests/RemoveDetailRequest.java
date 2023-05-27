package lv.javaguru.java2.servify.core.dto.requests;

public class RemoveDetailRequest {

    private Long id;

    public RemoveDetailRequest(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
