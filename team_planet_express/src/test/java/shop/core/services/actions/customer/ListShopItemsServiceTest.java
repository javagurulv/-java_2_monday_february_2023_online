//package shop.core.services.actions.customer;
//
//import org.junit.Ignore;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import shop.core.database.ItemRepository;
//import shop.core.domain.user.User;
//import shop.core.requests.customer.ListShopItemsRequest;
//import shop.core.services.validators.universal.system.DatabaseAccessValidator;
//import shop.core.support.CurrentUserId;
//
//import static org.mockito.ArgumentMatchers.anyLong;
//import static org.mockito.Mockito.verify;
//import static org.mockito.Mockito.when;
//
//@ExtendWith(MockitoExtension.class)
//
//@Ignore
//class ListShopItemsServiceTest {
//
//    @Mock
//    private ItemRepository mockItemRepository;
//    @Mock
//    private DatabaseAccessValidator mockDatabaseAccessValidator;
//    @Mock
//    private ListShopItemsRequest mockRequest;
//    @Mock
//    private User mockUser;
//    @Mock
//    private CurrentUserId mockCurrentUserId;
//
//    @InjectMocks
//    private ListShopItemsService service;
//
//    @Test
//    void shouldGetItemsFromDatabase() {
//        when(mockDatabaseAccessValidator.getUserById(anyLong())).thenReturn(mockUser);
//        when(mockRequest.getCurrentUserId()).thenReturn(mockCurrentUserId);
//        service.execute(mockRequest);
//        verify(mockItemRepository).getAllItems();
//    }
//
//}
