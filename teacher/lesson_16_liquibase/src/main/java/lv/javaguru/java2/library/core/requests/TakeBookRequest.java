package lv.javaguru.java2.library.core.requests;

public class TakeBookRequest {

    private Long readerId;
    private Long bookId;

    public TakeBookRequest() { }

    public TakeBookRequest(Long readerId, Long bookId) {
        this.readerId = readerId;
        this.bookId = bookId;
    }

    public Long getReaderId() {
        return readerId;
    }

    public void setReaderId(Long readerId) {
        this.readerId = readerId;
    }

    public Long getBookId() {
        return bookId;
    }

    public void setBookId(Long bookId) {
        this.bookId = bookId;
    }
}
