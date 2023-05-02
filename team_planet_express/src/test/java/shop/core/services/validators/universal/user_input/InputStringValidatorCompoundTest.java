package shop.core.services.validators.universal.user_input;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class InputStringValidatorCompoundTest {
/*
    @Mock
    private InputStringValidatorData mockInputStringValidatorData;

    @InjectMocks
    private InputStringValidator validator;

    @Test
    void shouldReturnOneErrorForLettersInIsNumberNotNegative() {
        when(mockInputStringValidatorData.getValue()).thenReturn("abc");
        when(mockInputStringValidatorData.isPresentChecker()).thenReturn(false);
        when(mockInputStringValidatorData.isGreaterZeroChecker()).thenReturn(false);
        when(mockInputStringValidatorData.isNotDecimalChecker()).thenReturn(false);
        when(mockInputStringValidatorData.isNumberChecker()).thenReturn(true);
        when(mockInputStringValidatorData.isNotNegativeChecker()).thenReturn(true);
        List<CoreError> errors = validator.validate(mockInputStringValidatorData);
        assertEquals(1, errors.size());
        assertCorrectErrorIsPresent(errors.get(0), "number");
    }

    @Test
    void shouldReturnOneErrorForNegativeInIsNumberNotNegative() {
        when(mockInputStringValidatorData.getValue()).thenReturn("-12.7");
        when(mockInputStringValidatorData.isPresentChecker()).thenReturn(false);
        when(mockInputStringValidatorData.isGreaterZeroChecker()).thenReturn(false);
        when(mockInputStringValidatorData.isNotDecimalChecker()).thenReturn(false);
        when(mockInputStringValidatorData.isNumberChecker()).thenReturn(true);
        when(mockInputStringValidatorData.isNotNegativeChecker()).thenReturn(true);
        List<CoreError> errors = validator.validate(mockInputStringValidatorData);
        assertEquals(1, errors.size());
        assertCorrectErrorIsPresent(errors.get(0), "negative");
    }

    @Test
    void shouldReturnOneErrorForLettersInIsNumberNotNegativeNotDecimal() {
        when(mockInputStringValidatorData.getValue()).thenReturn("abc");
        when(mockInputStringValidatorData.isPresentChecker()).thenReturn(false);
        when(mockInputStringValidatorData.isNumberChecker()).thenReturn(true);
        when(mockInputStringValidatorData.isGreaterZeroChecker()).thenReturn(false);
        when(mockInputStringValidatorData.isNotNegativeChecker()).thenReturn(true);
        when(mockInputStringValidatorData.isNotDecimalChecker()).thenReturn(true);
        List<CoreError> errors = validator.validate(mockInputStringValidatorData);
        assertEquals(1, errors.size());
        assertCorrectErrorIsPresent(errors.get(0), "number");
    }

    @Test
    void shouldReturnOneErrorForNegativeNumberInIsNumberNotNegativeNotDecimal() {
        when(mockInputStringValidatorData.getValue()).thenReturn("-17");
        when(mockInputStringValidatorData.isPresentChecker()).thenReturn(false);
        when(mockInputStringValidatorData.isNumberChecker()).thenReturn(true);
        when(mockInputStringValidatorData.isGreaterZeroChecker()).thenReturn(false);
        when(mockInputStringValidatorData.isNotNegativeChecker()).thenReturn(true);
        when(mockInputStringValidatorData.isNotDecimalChecker()).thenReturn(true);
        List<CoreError> errors = validator.validate(mockInputStringValidatorData);
        assertEquals(1, errors.size());
        assertCorrectErrorIsPresent(errors.get(0), "negative");
    }

    @Test
    void shouldReturnOneErrorForDecimalNumberInIsNumberNotNegativeNotDecimal() {
        when(mockInputStringValidatorData.getValue()).thenReturn("11.7");
        when(mockInputStringValidatorData.isPresentChecker()).thenReturn(false);
        when(mockInputStringValidatorData.isNumberChecker()).thenReturn(true);
        when(mockInputStringValidatorData.isGreaterZeroChecker()).thenReturn(false);
        when(mockInputStringValidatorData.isNotNegativeChecker()).thenReturn(true);
        when(mockInputStringValidatorData.isNotDecimalChecker()).thenReturn(true);
        List<CoreError> errors = validator.validate(mockInputStringValidatorData);
        assertEquals(1, errors.size());
        assertCorrectErrorIsPresent(errors.get(0), "decimal");
    }

    @Test
    void shouldReturnTwoErrorsForNegativeDecimalNumberInIsNumberNotNegativeNotDecimal() {
        when(mockInputStringValidatorData.getValue()).thenReturn("-12.7");
        when(mockInputStringValidatorData.isPresentChecker()).thenReturn(false);
        when(mockInputStringValidatorData.isNumberChecker()).thenReturn(true);
        when(mockInputStringValidatorData.isGreaterZeroChecker()).thenReturn(false);
        when(mockInputStringValidatorData.isNotNegativeChecker()).thenReturn(true);
        when(mockInputStringValidatorData.isNotDecimalChecker()).thenReturn(true);
        List<CoreError> errors = validator.validate(mockInputStringValidatorData);
        assertEquals(2, errors.size());
        assertCorrectErrorIsPresent(errors.get(0), "negative");
        assertCorrectErrorIsPresent(errors.get(1), "decimal");
    }

    @Test
    void shouldReturnOneErrorForLettersInIsNumberGreaterThanZeroNotDecimal() {
        when(mockInputStringValidatorData.getValue()).thenReturn("abc");
        when(mockInputStringValidatorData.isPresentChecker()).thenReturn(false);
        when(mockInputStringValidatorData.isNumberChecker()).thenReturn(true);
        when(mockInputStringValidatorData.isGreaterZeroChecker()).thenReturn(true);
        when(mockInputStringValidatorData.isNotNegativeChecker()).thenReturn(false);
        when(mockInputStringValidatorData.isNotDecimalChecker()).thenReturn(true);
        List<CoreError> errors = validator.validate(mockInputStringValidatorData);
        assertEquals(1, errors.size());
        assertCorrectErrorIsPresent(errors.get(0), "number");
    }

    @Test
    void shouldReturnOneErrorForZeroInIsNumberGreaterThanZeroNotDecimal() {
        when(mockInputStringValidatorData.getValue()).thenReturn("0");
        when(mockInputStringValidatorData.isPresentChecker()).thenReturn(false);
        when(mockInputStringValidatorData.isNumberChecker()).thenReturn(true);
        when(mockInputStringValidatorData.isGreaterZeroChecker()).thenReturn(true);
        when(mockInputStringValidatorData.isNotNegativeChecker()).thenReturn(false);
        when(mockInputStringValidatorData.isNotDecimalChecker()).thenReturn(true);
        List<CoreError> errors = validator.validate(mockInputStringValidatorData);
        assertEquals(1, errors.size());
        assertCorrectErrorIsPresent(errors.get(0), "greater");
    }

    @Test
    void shouldReturnOneErrorForNegativeNumberInIsNumberGreaterThanZeroNotDecimal() {
        when(mockInputStringValidatorData.getValue()).thenReturn("-17");
        when(mockInputStringValidatorData.isPresentChecker()).thenReturn(false);
        when(mockInputStringValidatorData.isNumberChecker()).thenReturn(true);
        when(mockInputStringValidatorData.isGreaterZeroChecker()).thenReturn(true);
        when(mockInputStringValidatorData.isNotNegativeChecker()).thenReturn(false);
        when(mockInputStringValidatorData.isNotDecimalChecker()).thenReturn(true);
        List<CoreError> errors = validator.validate(mockInputStringValidatorData);
        assertEquals(1, errors.size());
        assertCorrectErrorIsPresent(errors.get(0), "greater");
    }

    @Test
    void shouldReturnOneErrorForDecimalNumberInIsNumberGreaterThanZeroNotDecimal() {
        when(mockInputStringValidatorData.getValue()).thenReturn("11.7");
        when(mockInputStringValidatorData.isPresentChecker()).thenReturn(false);
        when(mockInputStringValidatorData.isNumberChecker()).thenReturn(true);
        when(mockInputStringValidatorData.isGreaterZeroChecker()).thenReturn(true);
        when(mockInputStringValidatorData.isNotNegativeChecker()).thenReturn(false);
        when(mockInputStringValidatorData.isNotDecimalChecker()).thenReturn(true);
        List<CoreError> errors = validator.validate(mockInputStringValidatorData);
        assertEquals(1, errors.size());
        assertCorrectErrorIsPresent(errors.get(0), "decimal");
    }

    @Test
    void shouldReturnTwoErrorsForNegativeDecimalNumberInIsNumberGreaterThanZeroNotDecimal() {
        when(mockInputStringValidatorData.getValue()).thenReturn("-12.7");
        when(mockInputStringValidatorData.isPresentChecker()).thenReturn(false);
        when(mockInputStringValidatorData.isNumberChecker()).thenReturn(true);
        when(mockInputStringValidatorData.isGreaterZeroChecker()).thenReturn(true);
        when(mockInputStringValidatorData.isNotNegativeChecker()).thenReturn(false);
        when(mockInputStringValidatorData.isNotDecimalChecker()).thenReturn(true);
        List<CoreError> errors = validator.validate(mockInputStringValidatorData);
        assertEquals(2, errors.size());
        assertCorrectErrorIsPresent(errors.get(0), "greater");
        assertCorrectErrorIsPresent(errors.get(1), "decimal");
    }

    private void assertCorrectErrorIsPresent(CoreError error, String errorText) {
        assertEquals("field", error.getField());
        assertTrue(error.getMessage().contains("Field"));
        assertTrue(error.getMessage().toLowerCase().contains(errorText));
    }*/

}