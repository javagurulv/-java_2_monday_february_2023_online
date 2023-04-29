package shop.core.services.validators.universal.user_input;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.core.support.Placeholder;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class InputStringValidatorData {

    String value;
    List<Placeholder> placeholders;

    boolean isPresentChecker;
    boolean isNumberChecker;
    boolean isNotNegativeChecker;
    boolean isGreaterZeroChecker;
    boolean isNotDecimalChecker;

    public InputStringValidatorData(String value, List<Placeholder> placeholders) {
        this.value = value;
        this.placeholders = placeholders;
        this.isPresentChecker = false;
        this.isNumberChecker = false;
        this.isNotNegativeChecker = false;
        this.isGreaterZeroChecker = false;
        this.isNotDecimalChecker = false;
    }
}
