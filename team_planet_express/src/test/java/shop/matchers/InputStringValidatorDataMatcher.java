package shop.matchers;

import org.mockito.ArgumentMatcher;
import shop.core.services.validators.universal.user_input.InputStringValidatorData;

public class InputStringValidatorDataMatcher implements ArgumentMatcher<InputStringValidatorData> {

    private final String value;

    public InputStringValidatorDataMatcher(String value) {
        this.value = value;
    }

    @Override
    public boolean matches(InputStringValidatorData inputStringValidatorData) {
        return value.equals(inputStringValidatorData.getValue());
    }

}
