package shop.core.services.validators.universal.user_input;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shop.core.responses.CoreError;
import shop.core.support.ErrorCodeUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class InputStringValidator {

    private static final String REGEX_NUMBER = "-?[0-9]+(.[0-9]+)?";
    private static final String REGEX_NOT_NEGATIVE = "[0-9]+(.[0-9]+)?";
    private static final String REGEX_GREATER_ZERO = "0*[1-9][0-9]*(.[0-9]+)?";
    private static final String REGEX_NOT_DECIMAL = "-?[0-9]+";
    @Autowired
    private ErrorCodeUtil errorCodeUtil;

    public Optional<CoreError> validateIsPresent(InputStringValidatorData inputStringValidatorData) {
        return (inputStringValidatorData.getValue() == null || inputStringValidatorData.getValue().isBlank())
                ? Optional.of(errorCodeUtil.errorBuild("ERROR_CODE_4", inputStringValidatorData.getPlaceholders()))
                : Optional.empty();
    }

    public Optional<CoreError> validateIsNumber(InputStringValidatorData inputStringValidatorData) {
        return (exists(inputStringValidatorData.getValue()) &&
                !inputStringValidatorData.getValue().matches(REGEX_NUMBER))
                ? Optional.of(errorCodeUtil.errorBuild("ERROR_CODE_5", inputStringValidatorData.getPlaceholders()))
                : Optional.empty();
    }

    public Optional<CoreError> validateIsNotNegative(InputStringValidatorData inputStringValidatorData) {
        return (exists(inputStringValidatorData.getValue()) &&
                !inputStringValidatorData.getValue().matches(REGEX_NOT_NEGATIVE))
                ? Optional.of(errorCodeUtil.errorBuild("ERROR_CODE_6", inputStringValidatorData.getPlaceholders()))
                : Optional.empty();
    }

    public Optional<CoreError> validateIsGreaterThanZero(InputStringValidatorData inputStringValidatorData) {
        return (exists(inputStringValidatorData.getValue()) &&
                !inputStringValidatorData.getValue().matches(REGEX_GREATER_ZERO))
                ? Optional.of(errorCodeUtil.errorBuild("ERROR_CODE_7", inputStringValidatorData.getPlaceholders()))
                : Optional.empty();
    }

    public Optional<CoreError> validateIsNotDecimal(InputStringValidatorData inputStringValidatorData) {
        return (exists(inputStringValidatorData.getValue()) &&
                !inputStringValidatorData.getValue().matches(REGEX_NOT_DECIMAL))
                ? Optional.of(errorCodeUtil.errorBuild("ERROR_CODE_8", inputStringValidatorData.getPlaceholders()))
                : Optional.empty();
    }

    public List<CoreError> validate(InputStringValidatorData inputStringValidatorData) {
        List<CoreError> errors = new ArrayList<>();

        if (inputStringValidatorData.isPresentChecker())
            validateIsPresent(inputStringValidatorData).ifPresent(errors::add);
        if (errors.isEmpty() && inputStringValidatorData.isNumberChecker())
            validateIsNumber(inputStringValidatorData).ifPresent(errors::add);
        if (errors.isEmpty() && inputStringValidatorData.isGreaterZeroChecker())
            validateIsGreaterThanZero(inputStringValidatorData).ifPresent(errors::add);
        if (errors.isEmpty() && inputStringValidatorData.isNotDecimalChecker())
            validateIsNotDecimal(inputStringValidatorData).ifPresent(errors::add);
        if (errors.isEmpty() && inputStringValidatorData.isNotNegativeChecker())
            validateIsNotNegative(inputStringValidatorData).ifPresent(errors::add);
        return errors;
    }

    private boolean exists(String value) {
        return value != null && !value.isBlank();
    }

}
