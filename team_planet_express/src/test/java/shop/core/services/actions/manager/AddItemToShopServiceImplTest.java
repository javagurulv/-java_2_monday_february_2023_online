package shop.core.services.actions.manager;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import shop.core.database.ItemRepository;
import shop.core.services.validators.services_validators.manager.AddItemToShopValidator;
import shop.core_api.dto.item.ItemDTO;
import shop.core_api.dto.item.Money;
import shop.core_api.requests.manager.AddItemToShopRequest;
import shop.core_api.responses.CoreError;
import shop.core_api.responses.manager.AddItemToShopResponse;
import shop.matchers.ItemMatcher;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AddItemToShopServiceImplTest {

    @Mock
    private ItemRepository mockItemRepository;
    @Mock
    private AddItemToShopValidator mockValidator;
    @Mock
    private AddItemToShopRequest mockRequest;
    @Mock
    private ItemDTO mockItemDTO;
    @Mock
    private CoreError mockCoreError;

    @InjectMocks
    private AddItemToShopServiceImpl service;

    @Test
    void shouldReturnErrorsIfPresent() {
        when(mockValidator.validate(mockRequest)).thenReturn(List.of(mockCoreError, mockCoreError));
        AddItemToShopResponse response = service.execute(mockRequest);
        assertEquals(2, response.getErrors().size());
    }

    @Test
    void shouldReturnNoErrorsForValidRequest() {
        when(mockValidator.validate(mockRequest)).thenReturn(Collections.emptyList());
        when(mockRequest.getItemDTO()).thenReturn(mockItemDTO);
        when(mockItemDTO.getPrice()).thenReturn(Money.dollars(BigDecimal.valueOf(100.10)));
        when(mockItemDTO.getAvailableQuantity()).thenReturn(10);
        AddItemToShopResponse response = service.execute(mockRequest);
        assertNull(response.getErrors());
    }

    @Test
    void shouldSaveValidRequest() {
        when(mockValidator.validate(mockRequest)).thenReturn(Collections.emptyList());
        when(mockRequest.getItemDTO()).thenReturn(mockItemDTO);
        when(mockItemDTO.getName()).thenReturn("name");
        when(mockItemDTO.getPrice()).thenReturn(Money.dollars(BigDecimal.valueOf(100.10)));
        when(mockItemDTO.getAvailableQuantity()).thenReturn(10);
        service.execute(mockRequest);
        verify(mockItemRepository)
                .save(argThat(new ItemMatcher("name", new BigDecimal("100.10"), 10)));
    }

    @Test
    void shouldRoundPriceUp() {
        when(mockValidator.validate(mockRequest)).thenReturn(Collections.emptyList());
        when(mockRequest.getItemDTO()).thenReturn(mockItemDTO);
        when(mockItemDTO.getName()).thenReturn("name");
        when(mockItemDTO.getPrice()).thenReturn(Money.dollars(BigDecimal.valueOf(100.10755)));
        when(mockItemDTO.getAvailableQuantity()).thenReturn(10);
        service.execute(mockRequest);
        verify(mockItemRepository)
                .save(argThat(new ItemMatcher("name", new BigDecimal("100.11"), 10)));
    }

    @Test
    void shouldRoundPriceDown() {
        when(mockValidator.validate(mockRequest)).thenReturn(Collections.emptyList());
        when(mockRequest.getItemDTO()).thenReturn(mockItemDTO);
        when(mockItemDTO.getName()).thenReturn("name");
        when(mockItemDTO.getPrice()).thenReturn(Money.dollars(BigDecimal.valueOf(99.102234)));
        when(mockItemDTO.getAvailableQuantity()).thenReturn(10);
        service.execute(mockRequest);
        verify(mockItemRepository)
                .save(argThat(new ItemMatcher("name", new BigDecimal("99.10"), 10)));
    }

}
