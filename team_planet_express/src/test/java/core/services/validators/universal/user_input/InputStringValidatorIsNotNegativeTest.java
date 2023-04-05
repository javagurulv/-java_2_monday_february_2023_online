package core.services.validators.universal.user_input;

import Shop.core.responses.CoreError;
import Shop.core.services.validators.universal.user_input.InputStringValidator;
import Shop.core.services.validators.universal.user_input.InputStringValidatorRecord;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InputStringValidatorIsNotNegativeTest {

    @Mock private InputStringValidatorRecord mockRecord;

    @InjectMocks private InputStringValidator validator;

    @Test
    void shouldReturnErrorForLetters() {
        when(mockRecord.value()).thenReturn("abc");
        when(mockRecord.field()).thenReturn("field");
        when(mockRecord.valueName()).thenReturn("Field");
        Optional<CoreError> error = validator.validateIsNotNegative(mockRecord);
        assertCorrectErrorIsPresent(error);
    }

    @Test
    void shouldReturnErrorForNonNumberValue() {
        when(mockRecord.value()).thenReturn("#&fml");
        when(mockRecord.field()).thenReturn("field");
        when(mockRecord.valueName()).thenReturn("Field");
        Optional<CoreError> error = validator.validateIsNotNegative(mockRecord);
        assertCorrectErrorIsPresent(error);
    }

    @Test
    void shouldReturnErrorForBorkedNumber() {
        when(mockRecord.value()).thenReturn("0-23.0040");
        when(mockRecord.field()).thenReturn("field");
        when(mockRecord.valueName()).thenReturn("Field");
        Optional<CoreError> error = validator.validateIsNotNegative(mockRecord);
        assertCorrectErrorIsPresent(error);
    }

    @Test
    void shouldReturnErrorForCharacterMess() {
        when(mockRecord.value()).thenReturn("132#re01-dd");
        when(mockRecord.field()).thenReturn("field");
        when(mockRecord.valueName()).thenReturn("Field");
        Optional<CoreError> error = validator.validateIsNotNegative(mockRecord);
        assertCorrectErrorIsPresent(error);
    }

    @Test
    void shouldReturnErrorForNegativeValue() {
        when(mockRecord.value()).thenReturn("-10");
        when(mockRecord.field()).thenReturn("field");
        when(mockRecord.valueName()).thenReturn("Field");
        Optional<CoreError> error = validator.validateIsNotNegative(mockRecord);
        assertCorrectErrorIsPresent(error);
    }

    @Test
    void shouldReturnErrorForNegativeNumberWithLeadingZeros() {
        when(mockRecord.value()).thenReturn("-00010");
        when(mockRecord.field()).thenReturn("field");
        when(mockRecord.valueName()).thenReturn("Field");
        Optional<CoreError> error = validator.validateIsNotNegative(mockRecord);
        assertCorrectErrorIsPresent(error);
    }

    @Test
    void shouldReturnErrorForNegativeDecimalNumber() {
        when(mockRecord.value()).thenReturn("-10.21");
        when(mockRecord.field()).thenReturn("field");
        when(mockRecord.valueName()).thenReturn("Field");
        Optional<CoreError> error = validator.validateIsNotNegative(mockRecord);
        assertCorrectErrorIsPresent(error);
    }

    @Test
    void shouldReturnErrorForNegativeDecimalNumberWithLeadingZeros() {
        when(mockRecord.value()).thenReturn("-010.21");
        when(mockRecord.field()).thenReturn("field");
        when(mockRecord.valueName()).thenReturn("Field");
        Optional<CoreError> error = validator.validateIsNotNegative(mockRecord);
        assertCorrectErrorIsPresent(error);
    }

    @Test
    void shouldReturnNoErrorForPositiveNumber() {
        when(mockRecord.value()).thenReturn("10");
        Optional<CoreError> error = validator.validateIsNotNegative(mockRecord);
        assertTrue(error.isEmpty());
    }

    @Test
    void shouldReturnNoErrorForPositiveNumberWithLeadingZeros() {
        when(mockRecord.value()).thenReturn("0010");
        Optional<CoreError> error = validator.validateIsNotNegative(mockRecord);
        assertTrue(error.isEmpty());
    }

    @Test
    void shouldReturnNoErrorForZero() {
        when(mockRecord.value()).thenReturn("0");
        Optional<CoreError> error = validator.validateIsNotNegative(mockRecord);
        assertTrue(error.isEmpty());
    }

    @Test
    void shouldReturnNoErrorForMultipleZeros() {
        when(mockRecord.value()).thenReturn("0000");
        Optional<CoreError> error = validator.validateIsNotNegative(mockRecord);
        assertTrue(error.isEmpty());
    }

    @Test
    void shouldReturnNoErrorForPositiveDecimalNumber() {
        when(mockRecord.value()).thenReturn("10.21");
        Optional<CoreError> error = validator.validateIsNotNegative(mockRecord);
        assertTrue(error.isEmpty());
    }

    @Test
    void shouldReturnNoErrorForPositiveDecimalNumberWithLeadingZeros() {
        when(mockRecord.value()).thenReturn("010.21");
        Optional<CoreError> error = validator.validateIsNotNegative(mockRecord);
        assertTrue(error.isEmpty());
    }

    private void assertCorrectErrorIsPresent(Optional<CoreError> error) {
        assertTrue(error.isPresent());
        assertEquals("field", error.get().getField());
        assertTrue(error.get().getMessage().contains("Field"));
        assertTrue(error.get().getMessage().toLowerCase().contains("negative"));
    }

}
