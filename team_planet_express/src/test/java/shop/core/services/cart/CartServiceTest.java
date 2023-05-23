package shop.core.services.cart;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import shop.core.database.CartItemRepository;
import shop.core.database.Repository;
import shop.core.domain.cart.Cart;
import shop.core.domain.cart_item.CartItem;
import shop.core.domain.item.Item;
import shop.core.services.validators.universal.system.DatabaseAccessValidator;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CartServiceTest {

    @Mock
    private Repository mockRepository;
    @Mock
    private DatabaseAccessValidator mockDatabaseAccessValidator;
    @Mock
    private CartItemRepository mockCartItemRepository;
    @Mock
    private CartItem mockCartItem;
    @Mock
    private Cart mockCart;
    @Mock
    private Item mockItem;

    @InjectMocks
    private CartService service;

    @Test
    void shouldReturnSum() {
        when(mockRepository.accessCartItemDatabase()).thenReturn(mockCartItemRepository);
        when(mockCartItemRepository.getAllCartItemsForCartId(mockCart)).thenReturn(List.of(mockCartItem, mockCartItem, mockCartItem));
        when(mockCartItem.getItem()).thenReturn(mockItem);
        when(mockDatabaseAccessValidator.getItemById(anyLong())).thenReturn(mockItem);
        when(mockItem.getPrice()).thenReturn(new BigDecimal("10"), new BigDecimal("7.52"), new BigDecimal("0.27"));
        when(mockCartItem.getOrderedQuantity()).thenReturn(1, 3, 7);
        BigDecimal actualResult = service.getSum(mockCart);
        assertEquals(new BigDecimal("34.45"), actualResult);
    }

}
