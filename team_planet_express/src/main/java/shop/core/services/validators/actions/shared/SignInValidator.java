package shop.core.services.validators.actions.shared;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shop.core.database.UserRepository;
import shop.core.requests.shared.SignInRequest;
import shop.core.responses.CoreError;
import shop.core.services.validators.universal.system.CurrentUserIdValidator;
import shop.core.services.validators.universal.system.DatabaseAccessValidator;
import shop.core.services.validators.universal.user_input.InputStringValidator;
import shop.core.services.validators.universal.user_input.InputStringValidatorData;
import shop.core.support.error_code_processing.ErrorProcessor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class SignInValidator {

    private static final String FIELD_LOGIN_NAME = "login";
    private static final String FIELD_PASSWORD = "password";
    private static final String VALUE_NAME_LOGIN = "Login name";
    private static final String VALUE_NAME_PASSWORD = "Password";
    private static final String ERROR_LOGIN_NOT_EXISTS = "VDT-SIN-LNE";
    private static final String ERROR_PASSWORD_INCORRECT = "VDT-SIN-PII";

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CurrentUserIdValidator userIdValidator;
    @Autowired
    private InputStringValidator inputStringValidator;
    @Autowired
    private DatabaseAccessValidator databaseAccessValidator;
    @Autowired
    private ErrorProcessor errorProcessor;

    public List<CoreError> validate(SignInRequest request) {
        userIdValidator.validateCurrentUserIdIsPresent(request.getCurrentUserId());
        List<CoreError> errors = new ArrayList<>();
        validateLoginName(request.getLoginName(), errors);
        validatePassword(request.getPassword(), errors);
        if (errors.isEmpty()) {
            validatePasswordMatches(request).ifPresent(errors::add);
        }
        return errors;
    }

    private void validateLoginName(String loginName, List<CoreError> errors) {
        InputStringValidatorData inputStringValidatorData =
                new InputStringValidatorData(loginName, FIELD_LOGIN_NAME, VALUE_NAME_LOGIN);
        inputStringValidator.validateIsPresent(inputStringValidatorData).ifPresent(errors::add);
        validateLoginNameExists(loginName).ifPresent(errors::add);
    }

    private void validatePassword(String password, List<CoreError> errors) {
        InputStringValidatorData inputStringValidatorData =
                new InputStringValidatorData(password, FIELD_PASSWORD, VALUE_NAME_PASSWORD);
        inputStringValidator.validateIsPresent(inputStringValidatorData).ifPresent(errors::add);
    }

    private Optional<CoreError> validatePasswordMatches(SignInRequest request) {
        return (!request.getPassword().equals(
                databaseAccessValidator.getUserByLoginName(request.getLoginName()).getPassword()))
                ? Optional.of(errorProcessor.getCoreError(FIELD_PASSWORD, ERROR_PASSWORD_INCORRECT))
                : Optional.empty();
    }

    private Optional<CoreError> validateLoginNameExists(String loginName) {
        return (loginName != null && !loginName.isBlank() &&
                userRepository.findByLoginName(loginName).isEmpty())
                ? Optional.of(errorProcessor.getCoreError(FIELD_LOGIN_NAME, ERROR_LOGIN_NOT_EXISTS))
                : Optional.empty();
    }

}
