package shop.matchers;

import org.mockito.ArgumentMatcher;
import shop.core.error_code_processing.TextReplacementData;

public class TextReplacementDataMatcher implements ArgumentMatcher<TextReplacementData> {

    private final String textToRemove;
    private final String textToAdd;

    public TextReplacementDataMatcher(String textToRemove, String textToAdd) {
        this.textToRemove = textToRemove;
        this.textToAdd = textToAdd;
    }

    @Override
    public boolean matches(TextReplacementData textReplacementData) {
        return textToRemove.equals(textReplacementData.getTextToRemove()) &&
                textToAdd.equals(textReplacementData.getTextToAdd());
    }

}
