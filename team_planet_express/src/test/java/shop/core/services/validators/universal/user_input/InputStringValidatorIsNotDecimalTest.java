package shop.core.services.validators.universal.user_input;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import shop.core.error_code_processing.ErrorProcessor;
import shop.core.error_code_processing.TextReplacementData;
import shop.core.responses.CoreError;
import shop.matchers.TextReplacementDataMatcher;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InputStringValidatorIsNotDecimalTest {

    @Mock
    private ErrorProcessor mockErrorProcessor;
    @Mock
    private InputStringValidatorData mockInputStringValidatorData;
    @Mock
    private CoreError mockCoreError;

    @InjectMocks
    private InputStringValidator validator;

    @Test
    void shouldReturnErrorForLetters() {
        when(mockInputStringValidatorData.getValue()).thenReturn("abc");
        when(mockInputStringValidatorData.getField()).thenReturn("field");
        when(mockInputStringValidatorData.getValueName()).thenReturn("Field");
        when(mockErrorProcessor.getCoreErrorWithTextReplacement(anyString(), anyString(), any(TextReplacementData.class))).thenReturn(mockCoreError);
        validator.validateIsNotDecimal(mockInputStringValidatorData);
        verifyCorrectGetCoreErrorCall();
    }

    @Test
    void shouldReturnErrorForNonNumberValue() {
        when(mockInputStringValidatorData.getValue()).thenReturn("#&fml");
        when(mockInputStringValidatorData.getField()).thenReturn("field");
        when(mockInputStringValidatorData.getValueName()).thenReturn("Field");
        when(mockErrorProcessor.getCoreErrorWithTextReplacement(anyString(), anyString(), any(TextReplacementData.class))).thenReturn(mockCoreError);
        validator.validateIsNotDecimal(mockInputStringValidatorData);
        verifyCorrectGetCoreErrorCall();
    }

    @Test
    void shouldReturnErrorForBrokenNumber() {
        when(mockInputStringValidatorData.getValue()).thenReturn("0-23.0040");
        when(mockInputStringValidatorData.getField()).thenReturn("field");
        when(mockInputStringValidatorData.getValueName()).thenReturn("Field");
        when(mockErrorProcessor.getCoreErrorWithTextReplacement(anyString(), anyString(), any(TextReplacementData.class))).thenReturn(mockCoreError);
        validator.validateIsNotDecimal(mockInputStringValidatorData);
        verifyCorrectGetCoreErrorCall();
    }

    @Test
    void shouldReturnErrorForCharacterMess() {
        when(mockInputStringValidatorData.getValue()).thenReturn("132#re01-dd");
        when(mockInputStringValidatorData.getField()).thenReturn("field");
        when(mockInputStringValidatorData.getValueName()).thenReturn("Field");
        when(mockErrorProcessor.getCoreErrorWithTextReplacement(anyString(), anyString(), any(TextReplacementData.class))).thenReturn(mockCoreError);
        validator.validateIsNotDecimal(mockInputStringValidatorData);
        verifyCorrectGetCoreErrorCall();
    }

    @Test
    void shouldReturnErrorForPositiveDecimalNumber() {
        when(mockInputStringValidatorData.getValue()).thenReturn("10.21");
        when(mockInputStringValidatorData.getField()).thenReturn("field");
        when(mockInputStringValidatorData.getValueName()).thenReturn("Field");
        when(mockErrorProcessor.getCoreErrorWithTextReplacement(anyString(), anyString(), any(TextReplacementData.class))).thenReturn(mockCoreError);
        validator.validateIsNotDecimal(mockInputStringValidatorData);
        verifyCorrectGetCoreErrorCall();
    }

    @Test
    void shouldReturnErrorForPositiveDecimalNumberWithLeadingZeros() {
        when(mockInputStringValidatorData.getValue()).thenReturn("010.21");
        when(mockInputStringValidatorData.getField()).thenReturn("field");
        when(mockInputStringValidatorData.getValueName()).thenReturn("Field");
        when(mockErrorProcessor.getCoreErrorWithTextReplacement(anyString(), anyString(), any(TextReplacementData.class))).thenReturn(mockCoreError);
        validator.validateIsNotDecimal(mockInputStringValidatorData);
        verifyCorrectGetCoreErrorCall();
    }

    @Test
    void shouldReturnErrorForNegativeDecimalNumber() {
        when(mockInputStringValidatorData.getValue()).thenReturn("-10.21");
        when(mockInputStringValidatorData.getField()).thenReturn("field");
        when(mockInputStringValidatorData.getValueName()).thenReturn("Field");
        when(mockErrorProcessor.getCoreErrorWithTextReplacement(anyString(), anyString(), any(TextReplacementData.class))).thenReturn(mockCoreError);
        validator.validateIsNotDecimal(mockInputStringValidatorData);
        verifyCorrectGetCoreErrorCall();
    }

    @Test
    void shouldReturnErrorForNegativeDecimalNumberWithLeadingZeros() {
        when(mockInputStringValidatorData.getValue()).thenReturn("-010.21");
        when(mockInputStringValidatorData.getField()).thenReturn("field");
        when(mockInputStringValidatorData.getValueName()).thenReturn("Field");
        when(mockErrorProcessor.getCoreErrorWithTextReplacement(anyString(), anyString(), any(TextReplacementData.class))).thenReturn(mockCoreError);
        validator.validateIsNotDecimal(mockInputStringValidatorData);
        verifyCorrectGetCoreErrorCall();
    }

    @Test
    void shouldReturnNoErrorForPositiveNumber() {
        when(mockInputStringValidatorData.getValue()).thenReturn("10");
        Optional<CoreError> error = validator.validateIsNotDecimal(mockInputStringValidatorData);
        assertTrue(error.isEmpty());
    }

    @Test
    void shouldReturnNoErrorForPositiveNumberWithLeadingZeros() {
        when(mockInputStringValidatorData.getValue()).thenReturn("0010");
        Optional<CoreError> error = validator.validateIsNotDecimal(mockInputStringValidatorData);
        assertTrue(error.isEmpty());
    }

    @Test
    void shouldReturnNoErrorForNegativeNumber() {
        when(mockInputStringValidatorData.getValue()).thenReturn("-10");
        Optional<CoreError> error = validator.validateIsNotDecimal(mockInputStringValidatorData);
        assertTrue(error.isEmpty());
    }

    @Test
    void shouldReturnNoErrorForNegativeNumberWithLeadingZeros() {
        when(mockInputStringValidatorData.getValue()).thenReturn("-00010");
        Optional<CoreError> error = validator.validateIsNotDecimal(mockInputStringValidatorData);
        assertTrue(error.isEmpty());
    }

    @Test
    void shouldReturnNoErrorForZero() {
        when(mockInputStringValidatorData.getValue()).thenReturn("0");
        Optional<CoreError> error = validator.validateIsNotDecimal(mockInputStringValidatorData);
        assertTrue(error.isEmpty());
    }

    @Test
    void shouldReturnNoErrorForMultipleZeros() {
        when(mockInputStringValidatorData.getValue()).thenReturn("0000");
        Optional<CoreError> error = validator.validateIsNotDecimal(mockInputStringValidatorData);
        assertTrue(error.isEmpty());
    }

    private void verifyCorrectGetCoreErrorCall() {
        verify(mockErrorProcessor).getCoreErrorWithTextReplacement(eq("field"),
                eq("VDT-IST-VID"),
                argThat(new TextReplacementDataMatcher("value", "Field")));
    }

}
