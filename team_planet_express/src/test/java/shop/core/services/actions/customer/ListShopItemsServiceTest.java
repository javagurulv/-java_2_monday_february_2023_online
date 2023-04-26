package shop.core.services.actions.customer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import shop.core.database.Database;
import shop.core.database.ItemDatabase;
import shop.core.domain.user.User;
import shop.core.requests.customer.ListShopItemsRequest;
import shop.core.services.validators.universal.system.DatabaseAccessValidator;
import shop.core.support.CurrentUserId;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ListShopItemsServiceTest {

    @Mock
    private Database mockDatabase;
    @Mock
    private DatabaseAccessValidator mockDatabaseAccessValidator;
    @Mock
    private ItemDatabase mockItemDatabase;
    @Mock
    private ListShopItemsRequest mockRequest;
    @Mock
    private User mockUser;
    @Mock
    private CurrentUserId mockCurrentUserId;

    @InjectMocks
    private ListShopItemsService service;

    @Test
    void shouldGetItemsFromDatabase() {
        when(mockDatabase.accessItemDatabase()).thenReturn(mockItemDatabase);
        when(mockDatabaseAccessValidator.getUserById(anyLong())).thenReturn(mockUser);
        when(mockRequest.getUserId()).thenReturn(mockCurrentUserId);
        service.execute(mockRequest);
        verify(mockItemDatabase).getAllItems();
    }

}
