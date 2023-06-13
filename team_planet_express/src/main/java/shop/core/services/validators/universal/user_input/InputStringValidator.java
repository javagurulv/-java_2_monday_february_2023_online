package shop.core.services.validators.universal.user_input;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shop.core.error_code_processing.ErrorProcessor;
import shop.core.error_code_processing.TextReplacementData;
import shop.core.responses.CoreError;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class InputStringValidator {

    private static final String ERROR_MISSING = "VDT-IST-VIM";
    private static final String ERROR_NOT_NUMBER = "VDT-IST-VNN";
    private static final String ERROR_NEGATIVE = "VDT-IST-VIN";
    private static final String ERROR_ZERO_OR_LESS = "VDT-IST-VZL";
    private static final String ERROR_DECIMAL = "VDT-IST-VID";
    private static final String ERROR_TOO_LONG = "VDT-IST-VTL";
    private static final String TEXT_TO_REMOVE = "value";

    private static final String REGEX_NUMBER = "-?[0-9]+(.[0-9]+)?";
    private static final String REGEX_NOT_NEGATIVE = "[0-9]+(.[0-9]+)?";
    private static final String REGEX_GREATER_ZERO = "0*[1-9][0-9]*(.[0-9]+)?";
    private static final String REGEX_NOT_DECIMAL = "-?[0-9]+";

    @Autowired
    private ErrorProcessor errorProcessor;

    public Optional<CoreError> validateIsPresent(InputStringValidatorData inputStringValidatorData) {
        return (inputStringValidatorData.getValue() == null || inputStringValidatorData.getValue().isBlank())
                ? Optional.of(getCoreErrorFromErrorProcessor(inputStringValidatorData, ERROR_MISSING))
                : Optional.empty();
    }

    public Optional<CoreError> validateIsNumber(InputStringValidatorData inputStringValidatorData) {
        return (exists(inputStringValidatorData.getValue()) &&
                !inputStringValidatorData.getValue().matches(REGEX_NUMBER))
                ? Optional.of(getCoreErrorFromErrorProcessor(inputStringValidatorData, ERROR_NOT_NUMBER))
                : Optional.empty();
    }

    public Optional<CoreError> validateIsNotNegative(InputStringValidatorData inputStringValidatorData) {
        return (exists(inputStringValidatorData.getValue()) &&
                !inputStringValidatorData.getValue().matches(REGEX_NOT_NEGATIVE))
                ? Optional.of(getCoreErrorFromErrorProcessor(inputStringValidatorData, ERROR_NEGATIVE))
                : Optional.empty();
    }

    public Optional<CoreError> validateIsGreaterThanZero(InputStringValidatorData inputStringValidatorData) {
        return (exists(inputStringValidatorData.getValue()) &&
                !inputStringValidatorData.getValue().matches(REGEX_GREATER_ZERO))
                ? Optional.of(getCoreErrorFromErrorProcessor(inputStringValidatorData, ERROR_ZERO_OR_LESS))
                : Optional.empty();
    }

    public Optional<CoreError> validateIsNotDecimal(InputStringValidatorData inputStringValidatorData) {
        return (exists(inputStringValidatorData.getValue()) &&
                !inputStringValidatorData.getValue().matches(REGEX_NOT_DECIMAL))
                ? Optional.of(getCoreErrorFromErrorProcessor(inputStringValidatorData, ERROR_DECIMAL))
                : Optional.empty();
    }

    public List<CoreError> validateIsNumberNotNegative(InputStringValidatorData inputStringValidatorData) {
        List<CoreError> errors = new ArrayList<>();
        Optional<CoreError> error = validateIsNumber(inputStringValidatorData);
        if (error.isPresent()) {
            errors.add(error.get());
        } else {
            validateIsNotNegative(inputStringValidatorData).ifPresent(errors::add);
        }
        return errors;
    }

    public List<CoreError> validateIsNumberNotNegativeNotDecimal(InputStringValidatorData inputStringValidatorData) {
        List<CoreError> errors = new ArrayList<>();
        Optional<CoreError> error = validateIsNumber(inputStringValidatorData);
        if (error.isPresent()) {
            errors.add(error.get());
        } else {
            validateIsNotNegative(inputStringValidatorData).ifPresent(errors::add);
            validateIsNotDecimal(inputStringValidatorData).ifPresent(errors::add);
        }
        return errors;
    }

    public List<CoreError> validateIsNumberGreaterThanZeroNotDecimal(InputStringValidatorData inputStringValidatorData) {
        List<CoreError> errors = new ArrayList<>();
        Optional<CoreError> error = validateIsNumber(inputStringValidatorData);
        if (error.isPresent()) {
            errors.add(error.get());
        } else {
            validateIsGreaterThanZero(inputStringValidatorData).ifPresent(errors::add);
            validateIsNotDecimal(inputStringValidatorData).ifPresent(errors::add);
        }
        return errors;
    }

    public Optional<CoreError> validateLength(InputStringValidatorData inputStringValidatorData, Integer maxLength) {
        return (exists(inputStringValidatorData.getValue()) &&
                inputStringValidatorData.getValue().length() > maxLength)
                ? Optional.of(getCoreErrorFromErrorProcessor(inputStringValidatorData, ERROR_TOO_LONG))
                : Optional.empty();
    }

    public Optional<CoreError> validateDecimalNumberLength(InputStringValidatorData inputStringValidatorData, Integer maxLength) {
        return (exists(inputStringValidatorData.getValue()) &&
                inputStringValidatorData.getValue().split("\\.")[0].length() > maxLength)
                ? Optional.of(getCoreErrorFromErrorProcessor(inputStringValidatorData, ERROR_TOO_LONG))
                : Optional.empty();
    }

    private boolean exists(String value) {
        return value != null && !value.isBlank();
    }

    private CoreError getCoreErrorFromErrorProcessor(InputStringValidatorData inputStringValidatorData, String errorCode) {
        return errorProcessor.getCoreErrorWithTextReplacement(
                inputStringValidatorData.getField(),
                errorCode,
                new TextReplacementData(TEXT_TO_REMOVE, inputStringValidatorData.getValueName()));
    }

}
