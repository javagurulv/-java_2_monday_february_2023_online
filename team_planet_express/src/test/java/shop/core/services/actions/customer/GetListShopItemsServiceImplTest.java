package shop.core.services.actions.customer;

import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import shop.core.database.ItemRepository;
import shop.core.domain.user.User;
import shop.core.services.actions.shared.SecurityServiceImpl;
import shop.core.services.validators.universal.system.DatabaseAccessValidator;
import shop.core_api.requests.customer.GetListShopItemsRequest;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)

@Ignore
class GetListShopItemsServiceImplTest {

    @Mock
    private ItemRepository mockItemRepository;
    @Mock
    private DatabaseAccessValidator mockDatabaseAccessValidator;
    @Mock
    private GetListShopItemsRequest mockRequest;
    @Mock
    private User mockUser;
    @Mock
    private SecurityServiceImpl mockSecurityService;

    @InjectMocks
    private GetListShopItemsServiceImpl service;

    @Test
    void shouldGetItemsFromDatabase() {
        service.execute(mockRequest);
        verify(mockItemRepository).getAllItems();
    }

}
