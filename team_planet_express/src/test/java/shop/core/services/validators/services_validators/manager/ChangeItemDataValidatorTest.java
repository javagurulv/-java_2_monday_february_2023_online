package shop.core.services.validators.services_validators.manager;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import shop.core.database.ItemRepository;
import shop.core.domain.item.Item;
import shop.core.services.validators.error_code_processing.ErrorProcessor;
import shop.core.services.validators.universal.user_input.InputStringValidator;
import shop.core.services.validators.universal.user_input.InputStringValidatorData;
import shop.core_api.dto.item.ItemDTO;
import shop.core_api.dto.item.Money;
import shop.core_api.requests.manager.ChangeItemDataRequest;
import shop.core_api.responses.CoreError;
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
    private ErrorProcessor mockErrorProcessor;
    @Mock
    private ChangeItemDataRequest mockRequest;
    @Mock
    private Item mockItem;
    @Mock
    private ItemDTO mockItemDTO;
    @Mock
    private CoreError mockCoreError;

    @InjectMocks
    private ChangeItemDataValidator validator;

    @Test
    void shouldValidateId() {
        when(mockRequest.getItemDTO()).thenReturn(mockItemDTO);
        when(mockItemDTO.getId()).thenReturn(1L);
        when(mockItemRepository.findById(1L)).thenReturn(Optional.of(mockItem));
        validator.validate(mockRequest);
        InputStringValidatorDataMatcher matcher =
                new InputStringValidatorDataMatcher("1", "id", "Item id");
        verify(mockInputStringValidator).validateIsPresent(argThat(matcher));
        verify(mockInputStringValidator).validateIsNumberNotNegativeNotDecimal(argThat(matcher));
    }

    @Test
    void shouldReturnErrorForNonexistentId() {
        when(mockRequest.getItemDTO()).thenReturn(mockItemDTO);
        when(mockItemDTO.getId()).thenReturn(1L);
        when(mockItemRepository.findById(1L)).thenReturn(Optional.empty());
        when(mockErrorProcessor.getCoreError(anyString(), anyString())).thenReturn(mockCoreError);
        validator.validate(mockRequest);
        verify(mockErrorProcessor).getCoreError("id", "VDT-CID-INE");
        verify(mockItemRepository).findById(1L);
    }

    @Test
    void shouldValidateName() {
        when(mockRequest.getItemDTO()).thenReturn(mockItemDTO);
        when(mockItemDTO.getId()).thenReturn(1L);
        when(mockItemDTO.getName()).thenReturn("name");
        when(mockItemRepository.findById(1L)).thenReturn(Optional.of(mockItem));
        validator.validate(mockRequest);
        verify(mockInputStringValidator)
                .validateLength(argThat(new InputStringValidatorDataMatcher("name", "name", "Item name")), anyInt());
    }

    @Test
    void shouldValidatePrice() {
        when(mockRequest.getItemDTO()).thenReturn(mockItemDTO);
        when(mockItemDTO.getId()).thenReturn(1L);
        when(mockItemDTO.getPrice()).thenReturn(Money.dollars(BigDecimal.valueOf(10.5)));
        when(mockItemRepository.findById(1L)).thenReturn(Optional.of(mockItem));
        List<CoreError> errorList = validator.validate(mockRequest);
        assertTrue(errorList.isEmpty());
    }

    @Test
    void shouldValidateQuantity() {
        when(mockRequest.getItemDTO()).thenReturn(mockItemDTO);
        when(mockItemDTO.getId()).thenReturn(1L);
        when(mockItemDTO.getAvailableQuantity()).thenReturn(5);
        when(mockItemRepository.findById(1L)).thenReturn(Optional.of(mockItem));
        List<CoreError> errorList = validator.validate(mockRequest);
        assertTrue(errorList.isEmpty());
    }

    @Test
    void shouldReturnErrorForDuplicate() {
        when(mockRequest.getItemDTO()).thenReturn(mockItemDTO);
        when(mockItemDTO.getId()).thenReturn(1L);
        when(mockItemDTO.getName()).thenReturn("name");
        when(mockItemDTO.getPrice()).thenReturn(Money.dollars(BigDecimal.valueOf(10.10)));
        when(mockItemDTO.getAvailableQuantity()).thenReturn(10);
        when(mockItemRepository.findById(1L)).thenReturn(Optional.of(mockItem));
        when(mockItemRepository.findAll()).thenReturn(List.of(new Item("name", new BigDecimal("10.10"), 10)));
        when(mockErrorProcessor.getCoreError(anyString(), anyString())).thenReturn(mockCoreError);
        validator.validate(mockRequest);
        verify(mockErrorProcessor).getCoreError("button", "VDT-CID-EIE");
    }

    @Test
    void shouldReturnMultipleErrors() {
        when(mockRequest.getItemDTO()).thenReturn(mockItemDTO);
        when(mockItemDTO.getId()).thenReturn(1L);
        when(mockInputStringValidator.validateIsNumberNotNegativeNotDecimal(any(InputStringValidatorData.class))).thenReturn(List.of(mockCoreError));
        List<CoreError> errors = validator.validate(mockRequest);
        assertTrue(errors.size() > 1);
    }

    @Test
    void shouldReturnNoErrorsForValidInput() {
        when(mockRequest.getItemDTO()).thenReturn(mockItemDTO);
        when(mockItemDTO.getId()).thenReturn(1L);
        when(mockItemRepository.findById(1L)).thenReturn(Optional.of(mockItem));
        List<CoreError> errors = validator.validate(mockRequest);
        assertTrue(errors.isEmpty());
    }

}
