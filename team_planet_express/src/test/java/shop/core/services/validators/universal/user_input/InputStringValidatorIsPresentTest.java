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
class InputStringValidatorIsPresentTest {

    @Mock
    private ErrorProcessor mockErrorProcessor;
    @Mock
    private InputStringValidatorData mockInputStringValidatorData;
    @Mock
    private CoreError mockCoreError;

    @InjectMocks
    private InputStringValidator validator;

    @Test
    void shouldReturnErrorForNullValue() {
        when(mockInputStringValidatorData.getValue()).thenReturn(null);
        when(mockInputStringValidatorData.getField()).thenReturn("field");
        when(mockInputStringValidatorData.getValueName()).thenReturn("Field");
        when(mockErrorProcessor.getCoreErrorWithTextReplacement(anyString(), anyString(), any(TextReplacementData.class))).thenReturn(mockCoreError);
        validator.validateIsPresent(mockInputStringValidatorData);
        verifyCorrectGetCoreErrorCall();
    }

    @Test
    void shouldReturnErrorForBlankValue() {
        when(mockInputStringValidatorData.getValue()).thenReturn("");
        when(mockInputStringValidatorData.getField()).thenReturn("field");
        when(mockInputStringValidatorData.getValueName()).thenReturn("Field");
        when(mockErrorProcessor.getCoreErrorWithTextReplacement(anyString(), anyString(), any(TextReplacementData.class))).thenReturn(mockCoreError);
        validator.validateIsPresent(mockInputStringValidatorData);
        verifyCorrectGetCoreErrorCall();
    }

    @Test
    void shouldReturnErrorForEmptyValue() {
        when(mockInputStringValidatorData.getValue()).thenReturn(" ");
        when(mockInputStringValidatorData.getField()).thenReturn("field");
        when(mockInputStringValidatorData.getValueName()).thenReturn("Field");
        when(mockErrorProcessor.getCoreErrorWithTextReplacement(anyString(), anyString(), any(TextReplacementData.class))).thenReturn(mockCoreError);
        validator.validateIsPresent(mockInputStringValidatorData);
        verifyCorrectGetCoreErrorCall();
    }

    @Test
    void shouldReturnNoErrorForValidValue() {
        when(mockInputStringValidatorData.getValue()).thenReturn("field");
        Optional<CoreError> error = validator.validateIsPresent(mockInputStringValidatorData);
        assertTrue(error.isEmpty());
    }

    private void verifyCorrectGetCoreErrorCall() {
        verify(mockErrorProcessor).getCoreErrorWithTextReplacement(eq("field"),
                eq("VDT-ISV-VIM"),
                argThat(new TextReplacementDataMatcher("value", "Field")));
    }

}
