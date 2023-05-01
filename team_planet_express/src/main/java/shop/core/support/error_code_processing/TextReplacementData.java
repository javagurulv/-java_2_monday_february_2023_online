package shop.core.support.error_code_processing;

import lombok.Value;

@Value
public class TextReplacementData {

    String textToRemove;
    String textToAdd;

}
