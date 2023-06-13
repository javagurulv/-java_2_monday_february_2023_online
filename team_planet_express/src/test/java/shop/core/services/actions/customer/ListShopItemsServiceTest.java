package shop.core.services.actions.customer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import shop.core.converters.ItemConverter;
import shop.core.database.ItemRepository;
import shop.core.requests.customer.ListShopItemsRequest;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ListShopItemsServiceTest {

    @Mock
    private ItemRepository mockItemRepository;
    @Mock
    private ItemConverter mockItemConverter;
    @Mock
    private ListShopItemsRequest mockRequest;

    @InjectMocks
    private ListShopItemsService service;

    @Test
    void shouldGetItemsFromDatabase() {
        service.execute(mockRequest);
        verify(mockItemRepository).getAllItems();
    }

}
