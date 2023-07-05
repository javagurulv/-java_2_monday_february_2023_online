package shop.core.services.validators.error_code_processing;

import org.springframework.stereotype.Component;
import shop.core_api.responses.CoreError;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

@Component
public class ErrorProcessor {

    private static final String ERROR_CODES_FILE_NAME = "errorCodes";
    private static final String GENERIC_ERROR_MESSAGE = "Error: Something went wrong.";

    private final ResourceBundle errorCodes;

    public ErrorProcessor() {
        this.errorCodes = ResourceBundle.getBundle(ERROR_CODES_FILE_NAME);
    }

    public CoreError getCoreError(String field, String errorCode) {
        try {
            return new CoreError(field, errorCode, errorCodes.getString(errorCode));
        } catch (MissingResourceException exception) {
            return new CoreError(field, errorCode, GENERIC_ERROR_MESSAGE);
        }
    }

    public CoreError getCoreErrorWithTextReplacement(String field, String errorCode, TextReplacementData textReplacement) {
        try {
            String message = getMessageWithTextReplacement(errorCode, textReplacement);
            return new CoreError(field, errorCode, message);
        } catch (MissingResourceException exception) {
            return new CoreError(field, errorCode, GENERIC_ERROR_MESSAGE);
        }
    }

    private String getMessageWithTextReplacement(String errorCode, TextReplacementData textReplacement) {
        return errorCodes.getString(errorCode)
                .replace("{" + textReplacement.getTextToRemove() + "}", textReplacement.getTextToAdd());
    }

}
