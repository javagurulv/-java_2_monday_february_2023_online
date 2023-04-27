package shop.core.services.validators.universal.user_input;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import shop.core.responses.CoreError;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InputStringValidatorIsNotNegativeTest {

    @Mock
    private InputStringValidatorData mockInputStringValidatorData;

    @InjectMocks
    private InputStringValidator validator;

    @Test
    void shouldReturnErrorForLetters() {
        when(mockInputStringValidatorData.getValue()).thenReturn("abc");
        when(mockInputStringValidatorData.getField()).thenReturn("field");
        when(mockInputStringValidatorData.getValueName()).thenReturn("Field");
        Optional<CoreError> error = validator.validateIsNotNegative(mockInputStringValidatorData);
        assertCorrectErrorIsPresent(error);
    }

    @Test
    void shouldReturnErrorForNonNumberValue() {
        when(mockInputStringValidatorData.getValue()).thenReturn("#&fml");
        when(mockInputStringValidatorData.getField()).thenReturn("field");
        when(mockInputStringValidatorData.getValueName()).thenReturn("Field");
        Optional<CoreError> error = validator.validateIsNotNegative(mockInputStringValidatorData);
        assertCorrectErrorIsPresent(error);
    }

    @Test
    void shouldReturnErrorForBrokenNumber() {
        when(mockInputStringValidatorData.getValue()).thenReturn("0-23.0040");
        when(mockInputStringValidatorData.getField()).thenReturn("field");
        when(mockInputStringValidatorData.getValueName()).thenReturn("Field");
        Optional<CoreError> error = validator.validateIsNotNegative(mockInputStringValidatorData);
        assertCorrectErrorIsPresent(error);
    }

    @Test
    void shouldReturnErrorForCharacterMess() {
        when(mockInputStringValidatorData.getValue()).thenReturn("132#re01-dd");
        when(mockInputStringValidatorData.getField()).thenReturn("field");
        when(mockInputStringValidatorData.getValueName()).thenReturn("Field");
        Optional<CoreError> error = validator.validateIsNotNegative(mockInputStringValidatorData);
        assertCorrectErrorIsPresent(error);
    }

    @Test
    void shouldReturnErrorForNegativeValue() {
        when(mockInputStringValidatorData.getValue()).thenReturn("-10");
        when(mockInputStringValidatorData.getField()).thenReturn("field");
        when(mockInputStringValidatorData.getValueName()).thenReturn("Field");
        Optional<CoreError> error = validator.validateIsNotNegative(mockInputStringValidatorData);
        assertCorrectErrorIsPresent(error);
    }

    @Test
    void shouldReturnErrorForNegativeNumberWithLeadingZeros() {
        when(mockInputStringValidatorData.getValue()).thenReturn("-00010");
        when(mockInputStringValidatorData.getField()).thenReturn("field");
        when(mockInputStringValidatorData.getValueName()).thenReturn("Field");
        Optional<CoreError> error = validator.validateIsNotNegative(mockInputStringValidatorData);
        assertCorrectErrorIsPresent(error);
    }

    @Test
    void shouldReturnErrorForNegativeDecimalNumber() {
        when(mockInputStringValidatorData.getValue()).thenReturn("-10.21");
        when(mockInputStringValidatorData.getField()).thenReturn("field");
        when(mockInputStringValidatorData.getValueName()).thenReturn("Field");
        Optional<CoreError> error = validator.validateIsNotNegative(mockInputStringValidatorData);
        assertCorrectErrorIsPresent(error);
    }

    @Test
    void shouldReturnErrorForNegativeDecimalNumberWithLeadingZeros() {
        when(mockInputStringValidatorData.getValue()).thenReturn("-010.21");
        when(mockInputStringValidatorData.getField()).thenReturn("field");
        when(mockInputStringValidatorData.getValueName()).thenReturn("Field");
        Optional<CoreError> error = validator.validateIsNotNegative(mockInputStringValidatorData);
        assertCorrectErrorIsPresent(error);
    }

    @Test
    void shouldReturnNoErrorForPositiveNumber() {
        when(mockInputStringValidatorData.getValue()).thenReturn("10");
        Optional<CoreError> error = validator.validateIsNotNegative(mockInputStringValidatorData);
        assertTrue(error.isEmpty());
    }

    @Test
    void shouldReturnNoErrorForPositiveNumberWithLeadingZeros() {
        when(mockInputStringValidatorData.getValue()).thenReturn("0010");
        Optional<CoreError> error = validator.validateIsNotNegative(mockInputStringValidatorData);
        assertTrue(error.isEmpty());
    }

    @Test
    void shouldReturnNoErrorForZero() {
        when(mockInputStringValidatorData.getValue()).thenReturn("0");
        Optional<CoreError> error = validator.validateIsNotNegative(mockInputStringValidatorData);
        assertTrue(error.isEmpty());
    }

    @Test
    void shouldReturnNoErrorForMultipleZeros() {
        when(mockInputStringValidatorData.getValue()).thenReturn("0000");
        Optional<CoreError> error = validator.validateIsNotNegative(mockInputStringValidatorData);
        assertTrue(error.isEmpty());
    }

    @Test
    void shouldReturnNoErrorForPositiveDecimalNumber() {
        when(mockInputStringValidatorData.getValue()).thenReturn("10.21");
        Optional<CoreError> error = validator.validateIsNotNegative(mockInputStringValidatorData);
        assertTrue(error.isEmpty());
    }

    @Test
    void shouldReturnNoErrorForPositiveDecimalNumberWithLeadingZeros() {
        when(mockInputStringValidatorData.getValue()).thenReturn("010.21");
        Optional<CoreError> error = validator.validateIsNotNegative(mockInputStringValidatorData);
        assertTrue(error.isEmpty());
    }

    private void assertCorrectErrorIsPresent(Optional<CoreError> error) {
        assertTrue(error.isPresent());
        assertEquals("field", error.get().getField());
        assertTrue(error.get().getMessage().contains("Field"));
        assertTrue(error.get().getMessage().toLowerCase().contains("negative"));
    }

}
