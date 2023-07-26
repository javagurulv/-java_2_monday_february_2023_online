package lv.javaguru.java2.library.core.requests;

public class SearchReadersAndBooksRequest {

	private String readerFirstName;
	private String readerLastName;

	private String bookTitle;
	private String bookAuthor;


	public SearchReadersAndBooksRequest() { }

	public SearchReadersAndBooksRequest(String readerFirstName,
										String readerLastName,
										String bookTitle,
										String bookAuthor) {
		this.readerFirstName = readerFirstName;
		this.readerLastName = readerLastName;
		this.bookTitle = bookTitle;
		this.bookAuthor = bookAuthor;
	}

	public String getReaderFirstName() {
		return readerFirstName;
	}

	public String getReaderLastName() {
		return readerLastName;
	}

	public String getBookTitle() {
		return bookTitle;
	}

	public String getBookAuthor() {
		return bookAuthor;
	}

	public void setReaderFirstName(String readerFirstName) {
		this.readerFirstName = readerFirstName;
	}

	public void setReaderLastName(String readerLastName) {
		this.readerLastName = readerLastName;
	}

	public void setBookTitle(String bookTitle) {
		this.bookTitle = bookTitle;
	}

	public void setBookAuthor(String bookAuthor) {
		this.bookAuthor = bookAuthor;
	}
}
