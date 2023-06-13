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

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InputStringValidatorCompoundTest {

    @Mock
    private ErrorProcessor mockErrorProcessor;
    @Mock
    private InputStringValidatorData mockInputStringValidatorData;
    @Mock
    private CoreError mockCoreError;

    @InjectMocks
    private InputStringValidator validator;

    @Test
    void shouldReturnOneErrorForLettersInIsNumberNotNegative() {
        when(mockInputStringValidatorData.getValue()).thenReturn("abc");
        when(mockInputStringValidatorData.getField()).thenReturn("field");
        when(mockInputStringValidatorData.getValueName()).thenReturn("Field");
        when(mockErrorProcessor.getCoreErrorWithTextReplacement(anyString(), anyString(), any(TextReplacementData.class))).thenReturn(mockCoreError);
        List<CoreError> errors = validator.validateIsNumberNotNegative(mockInputStringValidatorData);
        assertEquals(1, errors.size());
        verifyCorrectGetCoreErrorCall("VDT-IST-VNN");
    }

    @Test
    void shouldReturnOneErrorForNegativeInIsNumberNotNegative() {
        when(mockInputStringValidatorData.getValue()).thenReturn("-12.7");
        when(mockInputStringValidatorData.getField()).thenReturn("field");
        when(mockInputStringValidatorData.getValueName()).thenReturn("Field");
        when(mockErrorProcessor.getCoreErrorWithTextReplacement(anyString(), anyString(), any(TextReplacementData.class))).thenReturn(mockCoreError);
        List<CoreError> errors = validator.validateIsNumberNotNegative(mockInputStringValidatorData);
        assertEquals(1, errors.size());
        verifyCorrectGetCoreErrorCall("VDT-IST-VIN");
    }

    @Test
    void shouldReturnOneErrorForLettersInIsNumberNotNegativeNotDecimal() {
        when(mockInputStringValidatorData.getValue()).thenReturn("abc");
        when(mockInputStringValidatorData.getField()).thenReturn("field");
        when(mockInputStringValidatorData.getValueName()).thenReturn("Field");
        when(mockErrorProcessor.getCoreErrorWithTextReplacement(anyString(), anyString(), any(TextReplacementData.class))).thenReturn(mockCoreError);
        List<CoreError> errors = validator.validateIsNumberNotNegativeNotDecimal(mockInputStringValidatorData);
        assertEquals(1, errors.size());
        verifyCorrectGetCoreErrorCall("VDT-IST-VNN");
    }

    @Test
    void shouldReturnOneErrorForNegativeNumberInIsNumberNotNegativeNotDecimal() {
        when(mockInputStringValidatorData.getValue()).thenReturn("-17");
        when(mockInputStringValidatorData.getField()).thenReturn("field");
        when(mockInputStringValidatorData.getValueName()).thenReturn("Field");
        when(mockErrorProcessor.getCoreErrorWithTextReplacement(anyString(), anyString(), any(TextReplacementData.class))).thenReturn(mockCoreError);
        List<CoreError> errors = validator.validateIsNumberNotNegativeNotDecimal(mockInputStringValidatorData);
        assertEquals(1, errors.size());
        verifyCorrectGetCoreErrorCall("VDT-IST-VIN");
    }

    @Test
    void shouldReturnOneErrorForDecimalNumberInIsNumberNotNegativeNotDecimal() {
        when(mockInputStringValidatorData.getValue()).thenReturn("11.7");
        when(mockInputStringValidatorData.getField()).thenReturn("field");
        when(mockInputStringValidatorData.getValueName()).thenReturn("Field");
        when(mockErrorProcessor.getCoreErrorWithTextReplacement(anyString(), anyString(), any(TextReplacementData.class))).thenReturn(mockCoreError);
        List<CoreError> errors = validator.validateIsNumberNotNegativeNotDecimal(mockInputStringValidatorData);
        assertEquals(1, errors.size());
        verifyCorrectGetCoreErrorCall("VDT-IST-VID");
    }

    @Test
    void shouldReturnTwoErrorsForNegativeDecimalNumberInIsNumberNotNegativeNotDecimal() {
        when(mockInputStringValidatorData.getValue()).thenReturn("-12.7");
        when(mockInputStringValidatorData.getField()).thenReturn("field");
        when(mockInputStringValidatorData.getValueName()).thenReturn("Field");
        when(mockErrorProcessor.getCoreErrorWithTextReplacement(anyString(), anyString(), any(TextReplacementData.class))).thenReturn(mockCoreError);
        List<CoreError> errors = validator.validateIsNumberNotNegativeNotDecimal(mockInputStringValidatorData);
        assertEquals(2, errors.size());
        verifyCorrectGetCoreErrorCall("VDT-IST-VIN");
        verifyCorrectGetCoreErrorCall("VDT-IST-VID");
    }

    @Test
    void shouldReturnOneErrorForLettersInIsNumberGreaterThanZeroNotDecimal() {
        when(mockInputStringValidatorData.getValue()).thenReturn("abc");
        when(mockInputStringValidatorData.getField()).thenReturn("field");
        when(mockInputStringValidatorData.getValueName()).thenReturn("Field");
        when(mockErrorProcessor.getCoreErrorWithTextReplacement(anyString(), anyString(), any(TextReplacementData.class))).thenReturn(mockCoreError);
        List<CoreError> errors = validator.validateIsNumberGreaterThanZeroNotDecimal(mockInputStringValidatorData);
        assertEquals(1, errors.size());
        verifyCorrectGetCoreErrorCall("VDT-IST-VNN");
    }

    @Test
    void shouldReturnOneErrorForZeroInIsNumberGreaterThanZeroNotDecimal() {
        when(mockInputStringValidatorData.getValue()).thenReturn("0");
        when(mockInputStringValidatorData.getField()).thenReturn("field");
        when(mockInputStringValidatorData.getValueName()).thenReturn("Field");
        when(mockErrorProcessor.getCoreErrorWithTextReplacement(anyString(), anyString(), any(TextReplacementData.class))).thenReturn(mockCoreError);
        List<CoreError> errors = validator.validateIsNumberGreaterThanZeroNotDecimal(mockInputStringValidatorData);
        assertEquals(1, errors.size());
        verifyCorrectGetCoreErrorCall("VDT-IST-VZL");
    }

    @Test
    void shouldReturnOneErrorForNegativeNumberInIsNumberGreaterThanZeroNotDecimal() {
        when(mockInputStringValidatorData.getValue()).thenReturn("-17");
        when(mockInputStringValidatorData.getField()).thenReturn("field");
        when(mockInputStringValidatorData.getValueName()).thenReturn("Field");
        when(mockErrorProcessor.getCoreErrorWithTextReplacement(anyString(), anyString(), any(TextReplacementData.class))).thenReturn(mockCoreError);
        List<CoreError> errors = validator.validateIsNumberGreaterThanZeroNotDecimal(mockInputStringValidatorData);
        assertEquals(1, errors.size());
        verifyCorrectGetCoreErrorCall("VDT-IST-VZL");
    }

    @Test
    void shouldReturnOneErrorForDecimalNumberInIsNumberGreaterThanZeroNotDecimal() {
        when(mockInputStringValidatorData.getValue()).thenReturn("11.7");
        when(mockInputStringValidatorData.getField()).thenReturn("field");
        when(mockInputStringValidatorData.getValueName()).thenReturn("Field");
        when(mockErrorProcessor.getCoreErrorWithTextReplacement(anyString(), anyString(), any(TextReplacementData.class))).thenReturn(mockCoreError);
        List<CoreError> errors = validator.validateIsNumberGreaterThanZeroNotDecimal(mockInputStringValidatorData);
        assertEquals(1, errors.size());
        verifyCorrectGetCoreErrorCall("VDT-IST-VID");
    }

    @Test
    void shouldReturnTwoErrorsForNegativeDecimalNumberInIsNumberGreaterThanZeroNotDecimal() {
        when(mockInputStringValidatorData.getValue()).thenReturn("-12.7");
        when(mockInputStringValidatorData.getField()).thenReturn("field");
        when(mockInputStringValidatorData.getValueName()).thenReturn("Field");
        when(mockErrorProcessor.getCoreErrorWithTextReplacement(anyString(), anyString(), any(TextReplacementData.class))).thenReturn(mockCoreError);
        List<CoreError> errors = validator.validateIsNumberGreaterThanZeroNotDecimal(mockInputStringValidatorData);
        assertEquals(2, errors.size());
        verifyCorrectGetCoreErrorCall("VDT-IST-VZL");
        verifyCorrectGetCoreErrorCall("VDT-IST-VID");
    }

    private void verifyCorrectGetCoreErrorCall(String errorCode) {
        verify(mockErrorProcessor).getCoreErrorWithTextReplacement(eq("field"),
                eq(errorCode),
                argThat(new TextReplacementDataMatcher("value", "Field")));
    }

}
