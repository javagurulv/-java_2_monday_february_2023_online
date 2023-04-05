package core.services.actions.manager;

import Shop.core.database.Database;
import Shop.core.database.ItemDatabase;
import Shop.core.requests.manager.ChangeItemDataRequest;
import Shop.core.responses.CoreError;
import Shop.core.responses.manager.ChangeItemDataResponse;
import Shop.core.services.actions.manager.ChangeItemDataService;
import Shop.core.services.validators.actions.manager.ChangeItemDataValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ChangeItemDataServiceTest {

    @Mock private Database mockDatabase;
    @Mock private ChangeItemDataValidator mockValidator;
    @Mock private ChangeItemDataRequest mockRequest;
    @Mock private CoreError mockCoreError;
    @Mock private ItemDatabase mockItemDatabase;

    @InjectMocks private ChangeItemDataService service;

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
        when(mockDatabase.accessItemDatabase()).thenReturn(mockItemDatabase);
        service.execute(mockRequest);
        verify(mockItemDatabase).changeName(1L, "name");
    }

    @Test
    void shouldNotUpdateName() {
        when(mockValidator.validate(mockRequest)).thenReturn(Collections.emptyList());
        when(mockRequest.getItemId()).thenReturn("1");
        when(mockRequest.getNewItemName()).thenReturn("");
        service.execute(mockRequest);
        verify(mockItemDatabase, times(0)).changeName(anyLong(), anyString());
    }

    @Test
    void shouldUpdatePrice() {
        when(mockValidator.validate(mockRequest)).thenReturn(Collections.emptyList());
        when(mockRequest.getItemId()).thenReturn("1");
        when(mockRequest.getNewPrice()).thenReturn("10.10");
        when(mockDatabase.accessItemDatabase()).thenReturn(mockItemDatabase);
        service.execute(mockRequest);
        verify(mockItemDatabase).changePrice(1L, new BigDecimal("10.10"));
    }

    @Test
    void shouldNotUpdatePrice() {
        when(mockValidator.validate(mockRequest)).thenReturn(Collections.emptyList());
        when(mockRequest.getItemId()).thenReturn("1");
        when(mockRequest.getNewPrice()).thenReturn(" ");
        service.execute(mockRequest);
        verify(mockItemDatabase, times(0)).changePrice(anyLong(), any(BigDecimal.class));
    }

    @Test
    void shouldUpdateQuantity() {
        when(mockValidator.validate(mockRequest)).thenReturn(Collections.emptyList());
        when(mockRequest.getItemId()).thenReturn("1");
        when(mockRequest.getNewAvailableQuantity()).thenReturn("10");
        when(mockDatabase.accessItemDatabase()).thenReturn(mockItemDatabase);
        service.execute(mockRequest);
        verify(mockItemDatabase).changeAvailableQuantity(1L, 10);
    }

    @Test
    void shouldNotUpdateQuantity() {
        when(mockValidator.validate(mockRequest)).thenReturn(Collections.emptyList());
        when(mockRequest.getItemId()).thenReturn("1");
        when(mockRequest.getNewItemName()).thenReturn(null);
        service.execute(mockRequest);
        verify(mockItemDatabase, times(0)).changeAvailableQuantity(anyLong(), anyInt());
    }

    @Test
    void shouldRoundPriceUp() {
        when(mockValidator.validate(mockRequest)).thenReturn(Collections.emptyList());
        when(mockRequest.getItemId()).thenReturn("1");
        when(mockRequest.getNewPrice()).thenReturn("10.10776");
        when(mockDatabase.accessItemDatabase()).thenReturn(mockItemDatabase);
        service.execute(mockRequest);
        verify(mockItemDatabase).changePrice(1L, new BigDecimal("10.11"));
    }

    @Test
    void shouldRoundPriceDown() {
        when(mockValidator.validate(mockRequest)).thenReturn(Collections.emptyList());
        when(mockRequest.getItemId()).thenReturn("1");
        when(mockRequest.getNewPrice()).thenReturn("10.092234");
        when(mockDatabase.accessItemDatabase()).thenReturn(mockItemDatabase);
        service.execute(mockRequest);
        verify(mockItemDatabase).changePrice(1L, new BigDecimal("10.09"));

    }

}
