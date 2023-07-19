package lv.javaguru.java2.library.core.responses;

import lv.javaguru.java2.library.core.domain.Reader;

import java.util.List;

public class SearchReadersResponse extends CoreResponse {

	private List<Reader> readers;

	public SearchReadersResponse(List<Reader> readers, List<CoreError> errors) {
		super(errors);
		this.readers = readers;
	}

	public List<Reader> getReaders() {
		return readers;
	}
}
