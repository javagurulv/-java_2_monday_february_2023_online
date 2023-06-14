package shop.core.services.validators.actions.manager;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import shop.core.database.jpa.JpaItemRepository;
import shop.core.domain.Item;
import shop.core.error_code_processing.ErrorProcessor;
import shop.core.requests.manager.AddItemToShopRequest;
import shop.core.responses.CoreError;
import shop.core.services.validators.universal.user_input.InputStringValidator;
import shop.core.services.validators.universal.user_input.InputStringValidatorData;
import shop.matchers.InputStringValidatorDataMatcher;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AddItemToShopValidatorTest {

    @Mock
    private JpaItemRepository mockItemRepository;
    @Mock
    private InputStringValidator mockInputStringValidator;
    @Mock
    private ErrorProcessor mockErrorProcessor;
    @Mock
    private AddItemToShopRequest mockRequest;
    @Mock
    private Item mockItem;
    @Mock
    private CoreError mockCoreError;

    @InjectMocks
    private AddItemToShopValidator validator;

    @Test
    void shouldValidateName() {
        when(mockRequest.getItemName()).thenReturn("name");
        validator.validate(mockRequest);
        verify(mockInputStringValidator)
                .validateIsPresent(argThat(new InputStringValidatorDataMatcher("name", "name", "Item name")));
        verify(mockInputStringValidator)
                .validateLength(argThat(new InputStringValidatorDataMatcher("name", "name", "Item name")), anyInt());
    }

    @Test
    void shouldReturnErrorForExistingName() {
        when(mockRequest.getItemName()).thenReturn("name");
        when(mockItemRepository.findByName("name")).thenReturn(List.of(mockItem));
        when(mockErrorProcessor.getCoreError(anyString(), anyString())).thenReturn(mockCoreError);
        validator.validate(mockRequest);
        verify(mockErrorProcessor).getCoreError("name", "VDT-AIS-IAE");
    }

    @Test
    void shouldValidatePrice() {
        when(mockRequest.getPrice()).thenReturn("100.10");
        validator.validate(mockRequest);
        InputStringValidatorDataMatcher matcher =
                new InputStringValidatorDataMatcher("100.10", "price", "Price");
        verify(mockInputStringValidator).validateIsPresent(argThat(matcher));
        verify(mockInputStringValidator)
                .validateDecimalNumberLength(argThat(new InputStringValidatorDataMatcher("100.10", "price", "Price")), anyInt());
        verify(mockInputStringValidator).validateIsNumberNotNegative(argThat(matcher));
    }

    @Test
    void shouldValidateQuantity() {
        when(mockRequest.getAvailableQuantity()).thenReturn("10");
        validator.validate(mockRequest);
        InputStringValidatorDataMatcher matcher =
                new InputStringValidatorDataMatcher("10", "quantity", "Quantity");
        verify(mockInputStringValidator).validateIsPresent(argThat(matcher));
        verify(mockInputStringValidator).validateIsNumberNotNegativeNotDecimal(argThat(matcher));
    }

    @Test
    void shouldReturnMultipleErrors() {
        when(mockInputStringValidator.validateIsPresent(any(InputStringValidatorData.class))).thenReturn(Optional.of(mockCoreError));
        List<CoreError> errors = validator.validate(mockRequest);
        assertTrue(errors.size() > 1);
    }

    @Test
    void shouldReturnNoErrorsForValidInput() {
        List<CoreError> errors = validator.validate(mockRequest);
        assertTrue(errors.isEmpty());
    }

}
