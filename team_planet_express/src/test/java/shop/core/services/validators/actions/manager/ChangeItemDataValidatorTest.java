package shop.core.services.validators.actions.manager;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import shop.core.database.ItemRepository;
import shop.core.domain.Item;
import shop.core.error_code_processing.ErrorProcessor;
import shop.core.requests.manager.ChangeItemDataRequest;
import shop.core.responses.CoreError;
import shop.core.services.validators.universal.system.DatabaseAccessValidator;
import shop.core.services.validators.universal.user_input.InputStringValidator;
import shop.core.services.validators.universal.user_input.InputStringValidatorData;
import shop.matchers.InputStringValidatorDataMatcher;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ChangeItemDataValidatorTest {

    @Mock
    private ItemRepository mockItemRepository;
    @Mock
    private InputStringValidator mockInputStringValidator;
    @Mock
    private DatabaseAccessValidator mockDatabaseAccessValidator;
    @Mock
    private ErrorProcessor mockErrorProcessor;
    @Mock
    private ChangeItemDataRequest mockRequest;
    @Mock
    private Item mockItem;
    @Mock
    private CoreError mockCoreError;

    @InjectMocks
    private ChangeItemDataValidator validator;

    @Test
    void shouldValidateId() {
        when(mockRequest.getItemId()).thenReturn("1");
        when(mockItemRepository.findById(1L)).thenReturn(Optional.of(mockItem));
        when(mockDatabaseAccessValidator.getItemById(1L)).thenReturn(mockItem);
        validator.validate(mockRequest);
        InputStringValidatorDataMatcher matcher =
                new InputStringValidatorDataMatcher("1", "id", "Item id");
        verify(mockInputStringValidator).validateIsPresent(argThat(matcher));
        verify(mockInputStringValidator).validateIsNumberNotNegativeNotDecimal(argThat(matcher));
    }

    @Test
    void shouldReturnErrorForNonexistentId() {
        when(mockRequest.getItemId()).thenReturn("1");
        when(mockItemRepository.findById(1L)).thenReturn(Optional.empty());
        when(mockErrorProcessor.getCoreError(anyString(), anyString())).thenReturn(mockCoreError);
        validator.validate(mockRequest);
        verify(mockErrorProcessor).getCoreError("id", "VDT-CID-INE");
        verify(mockItemRepository).findById(1L);
    }

    @Test
    void shouldValidateName() {
        when(mockRequest.getItemId()).thenReturn("1");
        when(mockRequest.getNewItemName()).thenReturn("name");
        when(mockItemRepository.findById(1L)).thenReturn(Optional.of(mockItem));
        when(mockDatabaseAccessValidator.getItemById(1L)).thenReturn(mockItem);
        validator.validate(mockRequest);
        verify(mockInputStringValidator)
                .validateLength(argThat(new InputStringValidatorDataMatcher("name", "name", "Item name")), anyInt());
    }

    @Test
    void shouldValidatePrice() {
        when(mockRequest.getItemId()).thenReturn("1");
        when(mockRequest.getNewPrice()).thenReturn("10.5");
        when(mockItemRepository.findById(1L)).thenReturn(Optional.of(mockItem));
        when(mockDatabaseAccessValidator.getItemById(1L)).thenReturn(mockItem);
        validator.validate(mockRequest);
        InputStringValidatorDataMatcher matcher =
                new InputStringValidatorDataMatcher("10.5", "price", "Price");
        verify(mockInputStringValidator)
                .validateDecimalNumberLength(argThat(new InputStringValidatorDataMatcher("10.5", "price", "Price")), anyInt());
        verify(mockInputStringValidator).validateIsNumberNotNegative(argThat(matcher));
    }

    @Test
    void shouldValidateQuantity() {
        when(mockRequest.getItemId()).thenReturn("1");
        when(mockRequest.getNewAvailableQuantity()).thenReturn("5");
        when(mockItemRepository.findById(1L)).thenReturn(Optional.of(mockItem));
        when(mockDatabaseAccessValidator.getItemById(1L)).thenReturn(mockItem);
        validator.validate(mockRequest);
        InputStringValidatorDataMatcher matcher =
                new InputStringValidatorDataMatcher("5", "quantity", "Quantity");
        verify(mockInputStringValidator).validateIsNumberNotNegativeNotDecimal(argThat(matcher));
    }

    @Test
    void shouldReturnErrorForDuplicate() {
        when(mockRequest.getItemId()).thenReturn("1");
        when(mockRequest.getNewItemName()).thenReturn("name");
        when(mockRequest.getNewPrice()).thenReturn("10.10");
        when(mockRequest.getNewAvailableQuantity()).thenReturn("10");
        when(mockItemRepository.findById(1L)).thenReturn(Optional.of(mockItem));
        when(mockDatabaseAccessValidator.getItemById(1L)).thenReturn(mockItem);
        when(mockItemRepository.getAllItems()).thenReturn(List.of(new Item("name", new BigDecimal("10.10"), 10)));
        when(mockErrorProcessor.getCoreError(anyString(), anyString())).thenReturn(mockCoreError);
        validator.validate(mockRequest);
        verify(mockErrorProcessor).getCoreError("button", "VDT-CID-EIE");
    }

    @Test
    void shouldReturnMultipleErrors() {
        when(mockInputStringValidator.validateIsNumberNotNegativeNotDecimal(any(InputStringValidatorData.class))).thenReturn(List.of(mockCoreError));
        List<CoreError> errors = validator.validate(mockRequest);
        assertTrue(errors.size() > 1);
    }

    @Test
    void shouldReturnNoErrorsForValidInput() {
        when(mockRequest.getItemId()).thenReturn("1");
        when(mockItemRepository.findById(1L)).thenReturn(Optional.of(mockItem));
        when(mockDatabaseAccessValidator.getItemById(1L)).thenReturn(mockItem);
        List<CoreError> errors = validator.validate(mockRequest);
        assertTrue(errors.isEmpty());
    }

}
