package lv.javaguru.java2.servify.core.validators;


import lv.javaguru.java2.servify.core.requests.user.UpdateUserRequest;
import lv.javaguru.java2.servify.core.responses.CoreError;
import lv.javaguru.java2.servify.domain.FieldTitle;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UpdateUserValidator {
    public List<CoreError> validate(UpdateUserRequest request) {
        List<CoreError> errors = new ArrayList<>();
        validateFirstName(request).ifPresent(errors::add);
        validateLastName(request).ifPresent(errors::add);
        validateEmail(request).ifPresent(errors::add);
        validatePhoneNumber(request).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateFirstName(UpdateUserRequest request) {
        return (request.getFirstName() == null || request.getFirstName().isBlank())
                ? Optional.of(new CoreError(FieldTitle.FIRSTNAME, "Must not be empty"))
                : Optional.empty();
    }

    private Optional<CoreError> validateLastName(UpdateUserRequest request) {
        return (request.getLastName() == null || request.getLastName().isBlank())
                ? Optional.of(new CoreError(FieldTitle.LASTNAME, "Must not be empty"))
                : Optional.empty();
    }

    private Optional<CoreError> validateEmail(UpdateUserRequest request) {
        return (request.getEmail() == null || !request.getEmail().matches("\\w+@\\w+.\\D+"))
                ? Optional.of(new CoreError(FieldTitle.EMAIL, "Empty or wrong email! " +
                "\rHas to contain @ and domain with .extension!"))
                : Optional.empty();
    }

    private Optional<CoreError> validatePhoneNumber(UpdateUserRequest request) {
        return (request.getEmail() == null || !request.getPhoneNumber().matches("^[+]?\\d{8,13}"))
                ? Optional.of(new CoreError(FieldTitle.PHONE_NUMBER, "Empty or wrong phone number! " +
                "\rMust be not empty, and be from 8 to 13 digits - within international or local format. " +
                "\rFor example: " +
                "\r - international formats: +XXXXXXXXXXX; 00XXXXXXXXXXX" +
                "\r - local format: XXXXXXXX"))
                : Optional.empty();
    }
}
