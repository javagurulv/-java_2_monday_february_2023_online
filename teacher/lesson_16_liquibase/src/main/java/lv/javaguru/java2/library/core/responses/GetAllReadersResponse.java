package lv.javaguru.java2.library.core.responses;

import lv.javaguru.java2.library.core.domain.Reader;

import java.util.List;

public class GetAllReadersResponse extends CoreResponse {

	private List<Reader> readers;

	public GetAllReadersResponse(List<Reader> readers) {
		this.readers = readers;
	}

	public List<Reader> getReaders() {
		return readers;
	}
}
