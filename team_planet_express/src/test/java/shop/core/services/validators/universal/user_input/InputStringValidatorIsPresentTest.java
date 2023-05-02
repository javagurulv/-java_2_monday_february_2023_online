package shop.core.services.validators.universal.user_input;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class InputStringValidatorIsPresentTest {
/*
    @Mock
    private InputStringValidatorData mockInputStringValidatorData;

    @InjectMocks
    private InputStringValidator validator;

    @Test
    void shouldReturnErrorForNullValue() {
        when(mockInputStringValidatorData.getValue()).thenReturn(null);
        when(mockInputStringValidatorData.getField()).thenReturn("field");
        when(mockInputStringValidatorData.getValueName()).thenReturn("Field");
        Optional<CoreError> error = validator.validateIsPresent(mockInputStringValidatorData);
        assertCorrectErrorIsPresent(error);
    }

    @Test
    void shouldReturnErrorForBlankValue() {
        when(mockInputStringValidatorData.getValue()).thenReturn("");
        when(mockInputStringValidatorData.getField()).thenReturn("field");
        when(mockInputStringValidatorData.getValueName()).thenReturn("Field");
        Optional<CoreError> error = validator.validateIsPresent(mockInputStringValidatorData);
        assertCorrectErrorIsPresent(error);
    }

    @Test
    void shouldReturnErrorForEmptyValue() {
        when(mockInputStringValidatorData.getValue()).thenReturn(" ");
        when(mockInputStringValidatorData.getField()).thenReturn("field");
        when(mockInputStringValidatorData.getValueName()).thenReturn("Field");
        Optional<CoreError> error = validator.validateIsPresent(mockInputStringValidatorData);
        assertCorrectErrorIsPresent(error);
    }

    @Test
    void shouldReturnNoErrorForValidValue() {
        when(mockInputStringValidatorData.getValue()).thenReturn("field");
        Optional<CoreError> error = validator.validateIsPresent(mockInputStringValidatorData);
        assertTrue(error.isEmpty());
    }

    private void assertCorrectErrorIsPresent(Optional<CoreError> error) {
        assertTrue(error.isPresent());
        assertEquals("field", error.get().getField());
        assertTrue(error.get().getMessage().contains("Field"));
        assertTrue(error.get().getMessage().toLowerCase().contains("required"));
    }
*/
}
