package shop.core.services.validators.actions.guest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shop.core.database.jpa.JpaUserRepository;
import shop.core.error_code_processing.ErrorProcessor;
import shop.core.requests.guest.SignUpRequest;
import shop.core.responses.CoreError;
import shop.core.services.validators.universal.system.CurrentUserIdValidator;
import shop.core.services.validators.universal.user_input.InputStringValidator;
import shop.core.services.validators.universal.user_input.InputStringValidatorData;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class SignUpValidator {

    private static final String FIELD_NAME = "name";
    private static final String FIELD_LOGIN_NAME = "login";
    private static final String FIELD_PASSWORD = "password";
    private static final String VALUE_NAME_NAME = "Name";
    private static final String VALUE_NAME_LOGIN_NAME = "Login name";
    private static final String VALUE_NAME_PASSWORD = "Password";
    private static final String ERROR_LOGIN_EXISTS = "VDT-SUP-LAE";
    private static final String ERROR_PASSWORD_SHORT = "VDT-SUP-PTS";

    @Autowired
    private JpaUserRepository userRepository;
    @Autowired
    private CurrentUserIdValidator userIdValidator;
    @Autowired
    private InputStringValidator inputStringValidator;
    @Autowired
    private ErrorProcessor errorProcessor;

    public List<CoreError> validate(SignUpRequest request) {
        userIdValidator.validateCurrentUserIdIsPresent(request.getCurrentUserId());
        List<CoreError> errors = new ArrayList<>();
        validateName(request.getName(), errors);
        validateLoginName(request.getLoginName(), errors);
        validatePassword(request.getPassword(), errors);
        return errors;
    }

    private void validateName(String name, List<CoreError> errors) {
        InputStringValidatorData inputStringValidatorData =
                new InputStringValidatorData(name, FIELD_NAME, VALUE_NAME_NAME);
        inputStringValidator.validateIsPresent(inputStringValidatorData).ifPresent(errors::add);
        inputStringValidator.validateLength(inputStringValidatorData, 32).ifPresent(errors::add);
    }

    private void validateLoginName(String loginName, List<CoreError> errors) {
        InputStringValidatorData inputStringValidatorData =
                new InputStringValidatorData(loginName, FIELD_LOGIN_NAME, VALUE_NAME_LOGIN_NAME);
        inputStringValidator.validateIsPresent(inputStringValidatorData).ifPresent(errors::add);
        inputStringValidator.validateLength(inputStringValidatorData, 32).ifPresent(errors::add);
        validateLoginNameDoesNotAlreadyExist(loginName).ifPresent(errors::add);
    }

    private void validatePassword(String password, List<CoreError> errors) {
        InputStringValidatorData inputStringValidatorData =
                new InputStringValidatorData(password, FIELD_PASSWORD, VALUE_NAME_PASSWORD);
        inputStringValidator.validateIsPresent(inputStringValidatorData).ifPresent(errors::add);
        inputStringValidator.validateLength(inputStringValidatorData, 32).ifPresent(errors::add);
        validatePasswordLength(password).ifPresent(errors::add);
    }

    private Optional<CoreError> validateLoginNameDoesNotAlreadyExist(String loginName) {
        return (loginName != null && !loginName.isBlank() &&
                userRepository.findByLogin(loginName).stream().findFirst().isPresent())
                ? Optional.of(errorProcessor.getCoreError(FIELD_LOGIN_NAME, ERROR_LOGIN_EXISTS))
                : Optional.empty();
    }

    private Optional<CoreError> validatePasswordLength(String password) {
        return (password != null && !password.isBlank() && password.length() < 3)
                ? Optional.of(errorProcessor.getCoreError(FIELD_PASSWORD, ERROR_PASSWORD_SHORT))
                : Optional.empty();
    }

}
