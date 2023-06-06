package lv.javaguru.java2.servify.core.services.validators;

import lv.javaguru.java2.servify.core.domain.FieldTitle;
import lv.javaguru.java2.servify.core.dto.RegistrationDTO;
import lv.javaguru.java2.servify.core.dto.responses.CoreError;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RegistrationUserValidator {

    public List<CoreError> validate(RegistrationDTO registrDTO) {
        List<CoreError> errors = new ArrayList<>();
        validateFirstName(registrDTO).ifPresent(errors::add);
        validateLastName(registrDTO).ifPresent(errors::add);
        validateEmail(registrDTO).ifPresent(errors::add);
        validatePhoneNumber(registrDTO).ifPresent(errors::add);
        return errors;
    }

    private Optional<CoreError> validateFirstName(RegistrationDTO registrDTO) {
        return (registrDTO.getFirstName() == null || registrDTO.getFirstName().isBlank() || registrDTO.getFirstName().length() < 3)
                ? Optional.of(new CoreError(FieldTitle.FIRSTNAME, "Must not be empty or length must more then 3 characters"))
                : Optional.empty();
    }

    private Optional<CoreError> validateLastName(RegistrationDTO registrDTO) {
        return (registrDTO.getLastName() == null || registrDTO.getLastName().isBlank() || registrDTO.getLastName().length() < 3)
                ? Optional.of(new CoreError(FieldTitle.LASTNAME, "Must not be empty length must more then 3 characters"))
                : Optional.empty();
    }

    private Optional<CoreError> validateEmail(RegistrationDTO registrDTO) {
        return (registrDTO.getEmail() == null || !registrDTO.getEmail().matches("\\w+@\\w+.\\D+"))
                ? Optional.of(new CoreError(FieldTitle.EMAIL, "Empty or wrong email! " +
                "\rHas to contain @ and domain with .extension!"))
                : Optional.empty();
    }

    private Optional<CoreError> validatePhoneNumber(RegistrationDTO registrDTO) {
        return (registrDTO.getEmail() == null || !registrDTO.getPhoneNumber().matches("^[+]?\\d{8,13}"))
                ? Optional.of(new CoreError(FieldTitle.PHONE_NUMBER, "Empty or wrong phone number! " +
                "\rMust be not empty, and be from 8 to 13 digits - within international or local format. " +
                "\rFor example: " +
                "\r - international formats: +XXXXXXXXXXX; 00XXXXXXXXXXX" +
                "\r - local format: XXXXXXXX"))
                : Optional.empty();
    }

}
