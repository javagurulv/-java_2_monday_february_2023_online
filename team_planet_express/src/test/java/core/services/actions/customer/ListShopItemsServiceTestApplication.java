package core.services.actions.customer;

import Shop.core.database.Database;
import Shop.core.database.ItemDatabase;
import Shop.core.requests.customer.ListShopItemsRequest;
import Shop.core.services.actions.customer.ListShopItemsService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ListShopItemsServiceTestApplication {

    @Mock private Database mockDatabase;
    @Mock private ItemDatabase mockItemDatabase;
    @Mock private ListShopItemsRequest mockRequest;

    @InjectMocks private ListShopItemsService service;

    @Test
    void shouldGetItemsFromDatabase() {
        when(mockDatabase.accessItemDatabase()).thenReturn(mockItemDatabase);
        service.execute(mockRequest);
        verify(mockItemDatabase).getAllItems();
    }

}
