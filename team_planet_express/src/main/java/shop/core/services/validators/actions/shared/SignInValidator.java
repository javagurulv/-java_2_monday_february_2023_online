package shop.core.services.validators.actions.shared;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shop.core.database.Database;
import shop.core.requests.shared.SignInRequest;
import shop.core.responses.CoreError;
import shop.core.services.validators.universal.system.CurrentUserIdValidator;
import shop.core.services.validators.universal.system.DatabaseAccessValidator;
import shop.core.services.validators.universal.user_input.InputStringValidator;
import shop.core.services.validators.universal.user_input.InputStringValidatorData;
import shop.core.support.ErrorCodeUtil;
import shop.core.support.Placeholder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class SignInValidator {

    @Autowired
    private ErrorCodeUtil errorCodeUtil;
    @Autowired
    private Database database;
    @Autowired
    private CurrentUserIdValidator userIdValidator;
    @Autowired
    private InputStringValidator inputStringValidator;
    @Autowired
    private DatabaseAccessValidator databaseAccessValidator;

    public List<CoreError> validate(SignInRequest request) {
        userIdValidator.validateCurrentUserIdIsPresent(request.getUserId());
        List<CoreError> errors = new ArrayList<>();
        validateLoginName(request.getLoginName(), errors);
        validatePassword(request.getPassword(), errors);
        if (errors.isEmpty()) {
            validatePasswordMatches(request).ifPresent(errors::add);
        }
        return errors;
    }

    private void validateLoginName(String loginName, List<CoreError> errors) {
        List<Placeholder> placeholders = new ArrayList<>();
        placeholders.add(new Placeholder("VALUE", "Login name"));

        InputStringValidatorData inputStringValidatorData =
                new InputStringValidatorData(loginName, placeholders);
        inputStringValidatorData.setPresentChecker(true);

        errors.addAll(inputStringValidator.validate(inputStringValidatorData));
        validateLoginNameExists(loginName).ifPresent(errors::add);
    }

    private void validatePassword(String password, List<CoreError> errors) {
        List<Placeholder> placeholders = new ArrayList<>();
        placeholders.add(new Placeholder("VALUE", "Password"));

        InputStringValidatorData inputStringValidatorData =
                new InputStringValidatorData(password, placeholders);
        inputStringValidatorData.setPresentChecker(true);

        errors.addAll(inputStringValidator.validate(inputStringValidatorData));
    }

    private Optional<CoreError> validatePasswordMatches(SignInRequest request) {
        return (!request.getPassword().equals(
                databaseAccessValidator.getUserByLoginName(request.getLoginName()).getPassword()))
                ? Optional.of(errorCodeUtil.errorBuild("ERROR_CODE_15"))
                : Optional.empty();
    }

    private Optional<CoreError> validateLoginNameExists(String loginName) {
        return (loginName != null && !loginName.isBlank() &&
                database.accessUserDatabase().findByLoginName(loginName).isEmpty())
                ? Optional.of(errorCodeUtil.errorBuild("ERROR_CODE_14"))
                : Optional.empty();
    }

}
