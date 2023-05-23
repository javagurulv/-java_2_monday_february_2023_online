package shop.core.services.actions.customer;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import shop.core.database.Repository;
import shop.core.database.ItemRepository;
import shop.core.domain.user.User;
import shop.core.requests.customer.ListShopItemsRequest;
import shop.core.services.validators.universal.system.DatabaseAccessValidator;
import shop.core.support.CurrentUser;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ListShopItemsServiceTest {

    @Mock
    private Repository mockRepository;
    @Mock
    private DatabaseAccessValidator mockDatabaseAccessValidator;
    @Mock
    private ListShopItemsRequest mockRequest;
    @Mock
    private ItemRepository mockItemRepository;
    @Mock
    private User mockUser;
    @Mock
    private CurrentUser mockCurrentUser;

    @InjectMocks
    private ListShopItemsService service;

    @Test
    void shouldGetItemsFromDatabase() {
        when(mockRepository.accessItemRepository()).thenReturn(mockItemRepository);
        when(mockDatabaseAccessValidator.getUserById(anyLong())).thenReturn(mockUser);
        when(mockRequest.getCurrentUser()).thenReturn(mockCurrentUser);
        when(mockCurrentUser.getUser()).thenReturn(mockUser);
        when(mockUser.getUserRole()).thenReturn("GUEST");
        service.execute(mockRequest);
        verify(mockItemRepository).getAllItems();
    }

}
