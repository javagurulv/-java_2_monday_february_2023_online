package shop.core.services.validators.universal.user_input;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import shop.core.responses.CoreError;
import shop.core.support.error_code_processing.ErrorProcessor;
import shop.core.support.error_code_processing.TextReplacementData;
import shop.matchers.TextReplacementDataMatcher;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InputStringValidatorIsGreaterThenZeroTest {

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
        validator.validateIsGreaterThanZero(mockInputStringValidatorData);
        verifyCorrectGetCoreErrorCall();
    }

    @Test
    void shouldReturnErrorForNonNumberValue() {
        when(mockInputStringValidatorData.getValue()).thenReturn("#&fml");
        when(mockInputStringValidatorData.getField()).thenReturn("field");
        when(mockInputStringValidatorData.getValueName()).thenReturn("Field");
        when(mockErrorProcessor.getCoreErrorWithTextReplacement(anyString(), anyString(), any(TextReplacementData.class))).thenReturn(mockCoreError);
        validator.validateIsGreaterThanZero(mockInputStringValidatorData);
        verifyCorrectGetCoreErrorCall();
    }

    @Test
    void shouldReturnErrorForBrokenNumber() {
        when(mockInputStringValidatorData.getValue()).thenReturn("0-23.0040");
        when(mockInputStringValidatorData.getField()).thenReturn("field");
        when(mockInputStringValidatorData.getValueName()).thenReturn("Field");
        when(mockErrorProcessor.getCoreErrorWithTextReplacement(anyString(), anyString(), any(TextReplacementData.class))).thenReturn(mockCoreError);
        validator.validateIsGreaterThanZero(mockInputStringValidatorData);
        verifyCorrectGetCoreErrorCall();
    }

    @Test
    void shouldReturnErrorForCharacterMess() {
        when(mockInputStringValidatorData.getValue()).thenReturn("132#re01-dd");
        when(mockInputStringValidatorData.getField()).thenReturn("field");
        when(mockInputStringValidatorData.getValueName()).thenReturn("Field");
        when(mockErrorProcessor.getCoreErrorWithTextReplacement(anyString(), anyString(), any(TextReplacementData.class))).thenReturn(mockCoreError);
        validator.validateIsGreaterThanZero(mockInputStringValidatorData);
        verifyCorrectGetCoreErrorCall();
    }

    @Test
    void shouldReturnErrorForNegativeNumber() {
        when(mockInputStringValidatorData.getValue()).thenReturn("-10");
        when(mockInputStringValidatorData.getField()).thenReturn("field");
        when(mockInputStringValidatorData.getValueName()).thenReturn("Field");
        when(mockErrorProcessor.getCoreErrorWithTextReplacement(anyString(), anyString(), any(TextReplacementData.class))).thenReturn(mockCoreError);
        validator.validateIsGreaterThanZero(mockInputStringValidatorData);
        verifyCorrectGetCoreErrorCall();
    }

    @Test
    void shouldReturnErrorForNegativeNumberWithLeadingZeros() {
        when(mockInputStringValidatorData.getValue()).thenReturn("-00010");
        when(mockInputStringValidatorData.getField()).thenReturn("field");
        when(mockInputStringValidatorData.getValueName()).thenReturn("Field");
        when(mockErrorProcessor.getCoreErrorWithTextReplacement(anyString(), anyString(), any(TextReplacementData.class))).thenReturn(mockCoreError);
        validator.validateIsGreaterThanZero(mockInputStringValidatorData);
        verifyCorrectGetCoreErrorCall();
    }

    @Test
    void shouldReturnErrorForZero() {
        when(mockInputStringValidatorData.getValue()).thenReturn("0");
        when(mockInputStringValidatorData.getField()).thenReturn("field");
        when(mockInputStringValidatorData.getValueName()).thenReturn("Field");
        when(mockErrorProcessor.getCoreErrorWithTextReplacement(anyString(), anyString(), any(TextReplacementData.class))).thenReturn(mockCoreError);
        validator.validateIsGreaterThanZero(mockInputStringValidatorData);
        verifyCorrectGetCoreErrorCall();
    }

    @Test
    void shouldReturnErrorForMultipleZeros() {
        when(mockInputStringValidatorData.getValue()).thenReturn("0000");
        when(mockInputStringValidatorData.getField()).thenReturn("field");
        when(mockInputStringValidatorData.getValueName()).thenReturn("Field");
        when(mockErrorProcessor.getCoreErrorWithTextReplacement(anyString(), anyString(), any(TextReplacementData.class))).thenReturn(mockCoreError);
        validator.validateIsGreaterThanZero(mockInputStringValidatorData);
        verifyCorrectGetCoreErrorCall();
    }

    @Test
    void shouldReturnErrorForNegativeDecimalNumber() {
        when(mockInputStringValidatorData.getValue()).thenReturn("-10.21");
        when(mockInputStringValidatorData.getField()).thenReturn("field");
        when(mockInputStringValidatorData.getValueName()).thenReturn("Field");
        when(mockErrorProcessor.getCoreErrorWithTextReplacement(anyString(), anyString(), any(TextReplacementData.class))).thenReturn(mockCoreError);
        validator.validateIsGreaterThanZero(mockInputStringValidatorData);
        verifyCorrectGetCoreErrorCall();
    }

    @Test
    void shouldReturnErrorForNegativeDecimalNumberWithLeadingZeros() {
        when(mockInputStringValidatorData.getValue()).thenReturn("-010.21");
        when(mockInputStringValidatorData.getField()).thenReturn("field");
        when(mockInputStringValidatorData.getValueName()).thenReturn("Field");
        when(mockErrorProcessor.getCoreErrorWithTextReplacement(anyString(), anyString(), any(TextReplacementData.class))).thenReturn(mockCoreError);
        validator.validateIsGreaterThanZero(mockInputStringValidatorData);
        verifyCorrectGetCoreErrorCall();
    }

    @Test
    void shouldReturnNoErrorForPositiveNumber() {
        when(mockInputStringValidatorData.getValue()).thenReturn("22");
        Optional<CoreError> error = validator.validateIsGreaterThanZero(mockInputStringValidatorData);
        assertTrue(error.isEmpty());
    }

    @Test
    void shouldReturnNoErrorForPositiveNumberWithLeadingZeros() {
        when(mockInputStringValidatorData.getValue()).thenReturn("0010");
        Optional<CoreError> error = validator.validateIsGreaterThanZero(mockInputStringValidatorData);
        assertTrue(error.isEmpty());
    }

    @Test
    void shouldReturnNoErrorForPositiveDecimalNumber() {
        when(mockInputStringValidatorData.getValue()).thenReturn("10.21");
        Optional<CoreError> error = validator.validateIsGreaterThanZero(mockInputStringValidatorData);
        assertTrue(error.isEmpty());
    }

    @Test
    void shouldReturnNoErrorForPositiveDecimalNumberWithLeadingZeros() {
        when(mockInputStringValidatorData.getValue()).thenReturn("010.21");
        Optional<CoreError> error = validator.validateIsGreaterThanZero(mockInputStringValidatorData);
        assertTrue(error.isEmpty());
    }

    private void verifyCorrectGetCoreErrorCall() {
        verify(mockErrorProcessor).getCoreErrorWithTextReplacement(eq("field"),
                eq("VDT-ISV-VZL"),
                argThat(new TextReplacementDataMatcher("value", "Field")));
    }

}
