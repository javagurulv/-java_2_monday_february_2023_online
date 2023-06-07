package shop.core.services.actions.manager;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import shop.core.database.ItemRepository;
import shop.core.services.validators.actions.manager.ChangeItemDataValidator;
import shop.core_api.requests.manager.ChangeItemDataRequest;
import shop.core_api.responses.CoreError;
import shop.core_api.responses.manager.ChangeItemDataResponse;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

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
        when(mockRequest.getItemId()).thenReturn("1");
        ChangeItemDataResponse response = service.execute(mockRequest);
        assertNull(response.getErrors());
    }

    @Test
    void shouldUpdateName() {
        when(mockValidator.validate(mockRequest)).thenReturn(Collections.emptyList());
        when(mockRequest.getItemId()).thenReturn("1");
        when(mockRequest.getNewItemName()).thenReturn("name");
        service.execute(mockRequest);
        verify(mockItemRepository).changeName(1L, "name");
    }

    @Test
    void shouldNotUpdateName() {
        when(mockValidator.validate(mockRequest)).thenReturn(Collections.emptyList());
        when(mockRequest.getItemId()).thenReturn("1");
        when(mockRequest.getNewItemName()).thenReturn("");
        service.execute(mockRequest);
        verify(mockItemRepository, times(0)).changeName(anyLong(), anyString());
    }

    @Test
    void shouldUpdatePrice() {
        when(mockValidator.validate(mockRequest)).thenReturn(Collections.emptyList());
        when(mockRequest.getItemId()).thenReturn("1");
        when(mockRequest.getNewPrice()).thenReturn("10.10");
        service.execute(mockRequest);
        verify(mockItemRepository).changePrice(1L, new BigDecimal("10.10"));
    }

    @Test
    void shouldNotUpdatePrice() {
        when(mockValidator.validate(mockRequest)).thenReturn(Collections.emptyList());
        when(mockRequest.getItemId()).thenReturn("1");
        when(mockRequest.getNewPrice()).thenReturn(" ");
        service.execute(mockRequest);
        verify(mockItemRepository, times(0)).changePrice(anyLong(), any(BigDecimal.class));
    }

    @Test
    void shouldUpdateQuantity() {
        when(mockValidator.validate(mockRequest)).thenReturn(Collections.emptyList());
        when(mockRequest.getItemId()).thenReturn("1");
        when(mockRequest.getNewAvailableQuantity()).thenReturn("10");
        service.execute(mockRequest);
        verify(mockItemRepository).changeAvailableQuantity(1L, 10);
    }

    @Test
    void shouldNotUpdateQuantity() {
        when(mockValidator.validate(mockRequest)).thenReturn(Collections.emptyList());
        when(mockRequest.getItemId()).thenReturn("1");
        when(mockRequest.getNewItemName()).thenReturn(null);
        service.execute(mockRequest);
        verify(mockItemRepository, times(0)).changeAvailableQuantity(anyLong(), anyInt());
    }

    @Test
    void shouldRoundPriceUp() {
        when(mockValidator.validate(mockRequest)).thenReturn(Collections.emptyList());
        when(mockRequest.getItemId()).thenReturn("1");
        when(mockRequest.getNewPrice()).thenReturn("10.10776");
        service.execute(mockRequest);
        verify(mockItemRepository).changePrice(1L, new BigDecimal("10.11"));
    }

    @Test
    void shouldRoundPriceDown() {
        when(mockValidator.validate(mockRequest)).thenReturn(Collections.emptyList());
        when(mockRequest.getItemId()).thenReturn("1");
        when(mockRequest.getNewPrice()).thenReturn("10.092234");
        service.execute(mockRequest);
        verify(mockItemRepository).changePrice(1L, new BigDecimal("10.09"));

    }

}
