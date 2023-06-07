package shop.core.support.error_code_processing;

import org.junit.jupiter.api.Test;
import shop.core_api.responses.CoreError;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ErrorProcessorTest {

    private final ErrorProcessor errorProcessor = new ErrorProcessor();

    @Test
    void shouldReturnNoOpenCartError() {
        CoreError error = errorProcessor.getCoreError("field", "VDT-CRT-NOC");
        assertEquals("field", error.getField());
        assertEquals("VDT-CRT-NOC", error.getErrorCode());
        assertEquals("Error: You do not have an open cart.", error.getMessage());
    }

    @Test
    void shouldReturnErrorWithTextReplacement() {
        CoreError error = errorProcessor.getCoreErrorWithTextReplacement(
                "field",
                "VDT-IST-VIM",
                new TextReplacementData("value", "Field"));
        assertEquals("field", error.getField());
        assertEquals("VDT-IST-VIM", error.getErrorCode());
        assertEquals("Error: Field is required.", error.getMessage());
    }

    @Test
    void shouldReturnGenericErrorMessage() {
        CoreError error = errorProcessor.getCoreError("field", "AAA-BBB-CCC");
        assertEquals("field", error.getField());
        assertEquals("AAA-BBB-CCC", error.getErrorCode());
        assertEquals("Error: Something went wrong.", error.getMessage());
        error = errorProcessor.getCoreErrorWithTextReplacement(
                "field",
                "AAA-BBB-CCC",
                new TextReplacementData("value", "Field"));
        assertEquals("field", error.getField());
        assertEquals("AAA-BBB-CCC", error.getErrorCode());
        assertEquals("Error: Something went wrong.", error.getMessage());
    }

}
