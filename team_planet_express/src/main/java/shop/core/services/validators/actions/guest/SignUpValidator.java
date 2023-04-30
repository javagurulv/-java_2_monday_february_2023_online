package shop.core.services.validators.actions.guest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shop.core.database.Database;
import shop.core.requests.guest.SignUpRequest;
import shop.core.responses.CoreError;
import shop.core.services.validators.universal.system.CurrentUserIdValidator;
import shop.core.services.validators.universal.user_input.InputStringValidator;
import shop.core.services.validators.universal.user_input.InputStringValidatorData;
import shop.core.support.ErrorCodeUtil;
import shop.core.support.Placeholder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class SignUpValidator {
    @Autowired
    private ErrorCodeUtil errorCodeUtil;
    @Autowired
    private Database database;
    @Autowired
    private CurrentUserIdValidator userIdValidator;
    @Autowired
    private InputStringValidator inputStringValidator;

    public List<CoreError> validate(SignUpRequest request) {
        userIdValidator.validateCurrentUserIdIsPresent(request.getUserId());
        List<CoreError> errors = new ArrayList<>();
        validateName(request.getName(), errors);
        validateLoginName(request.getLoginName(), errors);
        validatePassword(request.getPassword(), errors);
        return errors;
    }

    private void validateName(String name, List<CoreError> errors) {
        List<Placeholder> placeholders = new ArrayList<>();
        placeholders.add(new Placeholder("VALUE", "Name"));

        InputStringValidatorData inputStringValidatorData =
                new InputStringValidatorData(name, placeholders);
        inputStringValidator.validateIsPresent(inputStringValidatorData).ifPresent(errors::add);
    }

    private void validateLoginName(String loginName, List<CoreError> errors) {
        List<Placeholder> placeholders = new ArrayList<>();
        placeholders.add(new Placeholder("VALUE", "Login name"));

        InputStringValidatorData inputStringValidatorData =
                new InputStringValidatorData(loginName, placeholders);
        inputStringValidator.validateIsPresent(inputStringValidatorData).ifPresent(errors::add);
        validateLoginNameDoesNotAlreadyExist(loginName).ifPresent(errors::add);
    }

    private void validatePassword(String password, List<CoreError> errors) {
        List<Placeholder> placeholders = new ArrayList<>();
        placeholders.add(new Placeholder("VALUE", "Password"));

        InputStringValidatorData inputStringValidatorData =
                new InputStringValidatorData(password, placeholders);
        inputStringValidator.validateIsPresent(inputStringValidatorData).ifPresent(errors::add);
        validatePasswordLength(password).ifPresent(errors::add);
    }

    private Optional<CoreError> validateLoginNameDoesNotAlreadyExist(String loginName) {
        return (loginName != null && !loginName.isBlank() &&
                database.accessUserDatabase().findByLoginName(loginName).isPresent())
                ? Optional.of(errorCodeUtil.errorBuild("ERROR_CODE_9"))
                : Optional.empty();
    }

    private Optional<CoreError> validatePasswordLength(String password) {
        return (password != null && !password.isBlank() && password.length() < 3)
                ? Optional.of(errorCodeUtil.errorBuild("ERROR_CODE_10"))
                : Optional.empty();
    }

}
