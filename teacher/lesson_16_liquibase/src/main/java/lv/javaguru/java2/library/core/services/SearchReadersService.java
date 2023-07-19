package lv.javaguru.java2.library.core.services;

import lv.javaguru.java2.library.core.database.jpa.JpaReaderRepository;
import lv.javaguru.java2.library.core.domain.Reader;
import lv.javaguru.java2.library.core.requests.SearchReadersRequest;
import lv.javaguru.java2.library.core.responses.CoreError;
import lv.javaguru.java2.library.core.responses.SearchReadersResponse;
import lv.javaguru.java2.library.core.services.validators.SearchReadersRequestValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Component
@Transactional
public class SearchReadersService {

	@Autowired private JpaReaderRepository readerRepository;
	@Autowired private SearchReadersRequestValidator validator;

	public SearchReadersResponse execute(SearchReadersRequest request) {
		List<CoreError> errors = validator.validate(request);
		if (!errors.isEmpty()) {
			return new SearchReadersResponse(null, errors);
		}

		List<Reader> readers = search(request);

		return new SearchReadersResponse(readers, null);
	}

	private List<Reader> search(SearchReadersRequest request) {
		List<Reader> readers = new ArrayList<>();
		if (request.isFirstNameProvided() && !request.isLastNameProvided()) {
			readers  = readerRepository.findByFirstNameLike(request.getFirstName());
		}
		if (!request.isFirstNameProvided() && request.isLastNameProvided()) {
			readers = readerRepository.findByLastNameLike(request.getLastName());
		}
		if (request.isFirstNameProvided() && request.isLastNameProvided()) {
			readers = readerRepository.findByFirstNameAndLastNameLike(request.getFirstName(), request.getLastName());
		}
		return readers;
	}

}
