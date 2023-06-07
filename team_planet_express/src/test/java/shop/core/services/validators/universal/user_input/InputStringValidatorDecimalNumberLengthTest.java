package shop.core.services.validators.universal.user_input;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import shop.core.support.error_code_processing.ErrorProcessor;
import shop.core.support.error_code_processing.TextReplacementData;
import shop.core_api.responses.CoreError;
import shop.matchers.TextReplacementDataMatcher;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InputStringValidatorDecimalNumberLengthTest {

    @Mock
    private ErrorProcessor mockErrorProcessor;
    @Mock
    private InputStringValidatorData mockInputStringValidatorData;
    @Mock
    private CoreError mockCoreError;

    @InjectMocks
    private InputStringValidator validator;

    @Test
    void shouldReturnErrorExceedingLength() {
        when(mockInputStringValidatorData.getValue()).thenReturn("1234567.123456789");
        when(mockInputStringValidatorData.getField()).thenReturn("field");
        when(mockInputStringValidatorData.getValueName()).thenReturn("Field");
        when(mockErrorProcessor.getCoreErrorWithTextReplacement(anyString(), anyString(), any(TextReplacementData.class))).thenReturn(mockCoreError);
        validator.validateDecimalNumberLength(mockInputStringValidatorData, 6);
        verifyCorrectGetCoreErrorCall();
    }

    @Test
    void shouldReturnErrorExceedingLengthWithNoDecimalPart() {
        when(mockInputStringValidatorData.getValue()).thenReturn("1234567");
        when(mockInputStringValidatorData.getField()).thenReturn("field");
        when(mockInputStringValidatorData.getValueName()).thenReturn("Field");
        when(mockErrorProcessor.getCoreErrorWithTextReplacement(anyString(), anyString(), any(TextReplacementData.class))).thenReturn(mockCoreError);
        validator.validateDecimalNumberLength(mockInputStringValidatorData, 6);
        verifyCorrectGetCoreErrorCall();
    }

    @Test
    void shouldReturnNoErrorForEqualLength() {
        when(mockInputStringValidatorData.getValue()).thenReturn("123456.123456789");
        Optional<CoreError> error = validator.validateDecimalNumberLength(mockInputStringValidatorData, 6);
        assertTrue(error.isEmpty());
    }

    @Test
    void shouldReturnNoErrorForShorterLength() {
        when(mockInputStringValidatorData.getValue()).thenReturn("1234.123456789");
        Optional<CoreError> error = validator.validateDecimalNumberLength(mockInputStringValidatorData, 6);
        assertTrue(error.isEmpty());
    }

    @Test
    void shouldReturnNoErrorForShorterLengthWithNoDecimalPart() {
        when(mockInputStringValidatorData.getValue()).thenReturn("1234");
        Optional<CoreError> error = validator.validateDecimalNumberLength(mockInputStringValidatorData, 6);
        assertTrue(error.isEmpty());
    }

    private void verifyCorrectGetCoreErrorCall() {
        verify(mockErrorProcessor).getCoreErrorWithTextReplacement(eq("field"),
                eq("VDT-IST-VTL"),
                argThat(new TextReplacementDataMatcher("value", "Field")));
    }

}
