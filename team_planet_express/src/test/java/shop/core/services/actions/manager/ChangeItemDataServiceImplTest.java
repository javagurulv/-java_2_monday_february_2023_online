package shop.core.services.actions.manager;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import shop.core.database.ItemRepository;
import shop.core.domain.item.Item;
import shop.core.services.validators.services_validators.manager.ChangeItemDataValidator;
import shop.core_api.dto.item.ItemDTO;
import shop.core_api.dto.item.Money;
import shop.core_api.requests.manager.ChangeItemDataRequest;
import shop.core_api.responses.CoreError;
import shop.core_api.responses.manager.ChangeItemDataResponse;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ChangeItemDataServiceImplTest {

    @Mock
    private ItemRepository mockItemRepository;
    @Mock
    private ChangeItemDataValidator mockValidator;
    @Mock
    private ChangeItemDataRequest mockRequest;
    @Mock
    private CoreError mockCoreError;
    @Mock
    private ItemDTO mockItemDTO;
    @Mock
    private Item mockItem;
    @Mock
    private Optional<Item> mockOptionalItem;

    @InjectMocks
    private ChangeItemDataServiceImpl service;

    @Test
    void shouldReturnErrorsIfPresent() {
        when(mockValidator.validate(mockRequest)).thenReturn(List.of(mockCoreError, mockCoreError));
        ChangeItemDataResponse response = service.execute(mockRequest);
        assertEquals(2, response.getErrors().size());
    }

    @Test
    void shouldReturnNoErrorsForValidRequest() {
        when(mockValidator.validate(mockRequest)).thenReturn(Collections.emptyList());
        when(mockRequest.getItemDTO()).thenReturn(mockItemDTO);
        when(mockItemDTO.getId()).thenReturn(1L);
        when(mockItemRepository.findById(1L)).thenReturn(mockOptionalItem);
        when(mockOptionalItem.get()).thenReturn(mockItem);
        ChangeItemDataResponse response = service.execute(mockRequest);
        assertNull(response.getErrors());
    }

    @Test
    void shouldUpdateName() {
        when(mockValidator.validate(mockRequest)).thenReturn(Collections.emptyList());
        when(mockRequest.getItemDTO()).thenReturn(mockItemDTO);
        when(mockItemDTO.getId()).thenReturn(1L);
        when(mockItemRepository.findById(1L)).thenReturn(mockOptionalItem);
        when(mockOptionalItem.get()).thenReturn(mockItem);
        when(mockItemDTO.getName()).thenReturn("name");
        service.execute(mockRequest);
        verify(mockItem).setName("name");
    }

    @Test
    void shouldNotUpdateName() {
        when(mockValidator.validate(mockRequest)).thenReturn(Collections.emptyList());
        when(mockRequest.getItemDTO()).thenReturn(mockItemDTO);
        when(mockItemDTO.getId()).thenReturn(1L);
        when(mockItemRepository.findById(1L)).thenReturn(mockOptionalItem);
        when(mockOptionalItem.get()).thenReturn(mockItem);
        service.execute(mockRequest);
        verify(mockItem, times(0)).setName(anyString());
    }

    @Test
    void shouldUpdatePrice() {
        when(mockValidator.validate(mockRequest)).thenReturn(Collections.emptyList());
        when(mockRequest.getItemDTO()).thenReturn(mockItemDTO);
        when(mockItemDTO.getId()).thenReturn(1L);
        when(mockItemRepository.findById(1L)).thenReturn(mockOptionalItem);
        when(mockOptionalItem.get()).thenReturn(mockItem);
        when(mockItemDTO.getPrice()).thenReturn(Money.dollars(new BigDecimal("10.10")));
        service.execute(mockRequest);
        verify(mockItem).setPrice(new BigDecimal("10.10"));
    }

    @Test
    void shouldNotUpdatePrice() {
        when(mockValidator.validate(mockRequest)).thenReturn(Collections.emptyList());
        when(mockRequest.getItemDTO()).thenReturn(mockItemDTO);
        when(mockItemDTO.getId()).thenReturn(1L);
        when(mockItemRepository.findById(1L)).thenReturn(mockOptionalItem);
        when(mockOptionalItem.get()).thenReturn(mockItem);
        service.execute(mockRequest);
        verify(mockItem, times(0)).setPrice(any(BigDecimal.class));
    }

    @Test
    void shouldUpdateQuantity() {
        when(mockValidator.validate(mockRequest)).thenReturn(Collections.emptyList());
        when(mockRequest.getItemDTO()).thenReturn(mockItemDTO);
        when(mockItemDTO.getId()).thenReturn(1L);
        when(mockItemRepository.findById(1L)).thenReturn(mockOptionalItem);
        when(mockOptionalItem.get()).thenReturn(mockItem);
        when(mockItemDTO.getAvailableQuantity()).thenReturn(10);
        service.execute(mockRequest);
        verify(mockItem).setAvailableQuantity(10);
    }

    @Test
    void shouldNotUpdateQuantity() {
        when(mockValidator.validate(mockRequest)).thenReturn(Collections.emptyList());
        when(mockRequest.getItemDTO()).thenReturn(mockItemDTO);
        when(mockItemDTO.getId()).thenReturn(1L);
        when(mockItemRepository.findById(1L)).thenReturn(mockOptionalItem);
        when(mockItemDTO.getAvailableQuantity()).thenReturn(null);
        when(mockOptionalItem.get()).thenReturn(mockItem);
        service.execute(mockRequest);
        verify(mockItem, times(0)).setAvailableQuantity(anyInt());
    }

    @Test
    void shouldRoundPriceUp() {
        when(mockValidator.validate(mockRequest)).thenReturn(Collections.emptyList());
        when(mockRequest.getItemDTO()).thenReturn(mockItemDTO);
        when(mockItemDTO.getId()).thenReturn(1L);
        when(mockItemRepository.findById(1L)).thenReturn(mockOptionalItem);
        when(mockOptionalItem.get()).thenReturn(mockItem);
        when(mockItemDTO.getPrice()).thenReturn(Money.dollars(new BigDecimal("10.10776")));
        service.execute(mockRequest);
        verify(mockItem).setPrice(new BigDecimal("10.11"));
    }

    @Test
    void shouldRoundPriceDown() {
        when(mockValidator.validate(mockRequest)).thenReturn(Collections.emptyList());
        when(mockRequest.getItemDTO()).thenReturn(mockItemDTO);
        when(mockItemDTO.getId()).thenReturn(1L);
        when(mockItemRepository.findById(1L)).thenReturn(mockOptionalItem);
        when(mockOptionalItem.get()).thenReturn(mockItem);
        when(mockItemDTO.getPrice()).thenReturn(Money.dollars(new BigDecimal("10.092234")));
        service.execute(mockRequest);
        verify(mockItem).setPrice(new BigDecimal("10.09"));
    }

}
