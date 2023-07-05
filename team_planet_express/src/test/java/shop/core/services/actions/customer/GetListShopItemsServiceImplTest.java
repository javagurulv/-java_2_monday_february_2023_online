package shop.core.services.actions.customer;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import shop.core.database.ItemRepository;
import shop.core_api.requests.customer.GetListShopItemsRequest;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)

@Ignore
class GetListShopItemsServiceImplTest {

    @Mock
    private ItemRepository mockItemRepository;
    @Mock
    private GetListShopItemsRequest mockRequest;

    @InjectMocks
    private GetListShopItemsServiceImpl service;

    @Test
    void shouldGetItemsFromDatabase() {
        service.execute(mockRequest);
        verify(mockItemRepository).findAll();
    }

}
