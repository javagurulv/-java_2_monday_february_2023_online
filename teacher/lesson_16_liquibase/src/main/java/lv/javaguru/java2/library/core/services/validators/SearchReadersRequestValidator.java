package lv.javaguru.java2.library.core.services.validators;

import lv.javaguru.java2.library.core.requests.SearchReadersRequest;
import lv.javaguru.java2.library.core.responses.CoreError;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SearchReadersRequestValidator {

	public List<CoreError> validate(SearchReadersRequest request) {
		List<CoreError> errors = new ArrayList<>();
		errors.addAll(validateSearchFields(request));
		return errors;
	}

	private List<CoreError> validateSearchFields(SearchReadersRequest request) {
		List<CoreError> errors = new ArrayList<>();
		if (isEmpty(request.getFirstName()) && isEmpty(request.getLastName())) {
			errors.add(new CoreError("firstName", "Must not be empty!"));
			errors.add(new CoreError("lastName", "Must not be empty!"));
		}
		return errors;
	}

	private boolean isEmpty(String str) {
		return str == null || str.isEmpty();
	}

}