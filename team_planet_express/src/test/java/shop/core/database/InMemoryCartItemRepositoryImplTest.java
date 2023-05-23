package shop.core.database;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import shop.core.database.in_memory.InMemoryCartItemRepositoryImpl;
import shop.core.domain.cart.Cart;
import shop.core.domain.cart_item.CartItem;
import shop.core.domain.item.Item;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InMemoryCartItemRepositoryImplTest {

    @Mock
    private Cart mockCart;
    @Mock
    private Item mockItem;
    @Mock
    private CartItem mockCartItem;

    @InjectMocks
    private InMemoryCartItemRepositoryImpl database;

    @Test
    void shouldIncreaseInSizeAfterSave() {
        database.save(mockCartItem);
        assertEquals(1, database.getCartItems().size());
    }

    @Test
    void shouldReturnFoundCartItem() {
        when(mockCartItem.getCart()).thenReturn(mockCart);
        when(mockCartItem.getItem()).thenReturn(mockItem);
        database.getCartItems().add(mockCartItem);
        assertTrue(database.findByCartIdAndItemId(mockCart, mockItem).isPresent());
    }

    @Disabled
    @Test
    void shouldReturnEmptyOptionalForNonexistentItem() {
        //TODO fml
        when(mockCartItem.getCart()).thenReturn(mockCart);
        when(mockCartItem.getItem()).thenReturn(mockItem);
        database.getCartItems().add(mockCartItem);
        assertTrue(database.findByCartIdAndItemId(mockCart, mockItem).isEmpty());
    }

    @Test
    void shouldDecreaseInSizeAfterRemove() {
        when(mockCartItem.getId()).thenReturn(1L);
        database.getCartItems().add(mockCartItem);
        database.deleteByID(1L);
        assertEquals(0, database.getCartItems().size());
    }

    //testForFailDelete

    @Test
    void shouldChangeOrderedQuantity() {
        when(mockCartItem.getId()).thenReturn(1L);
        database.getCartItems().add(mockCartItem);
        database.changeOrderedQuantity(1L, 10);
        verify(mockCartItem).setOrderedQuantity(10);
    }

    @Test
    void shouldReturn4CartItems() {
        database.getCartItems().add(mockCartItem);
        database.getCartItems().add(mockCartItem);
        database.getCartItems().add(mockCartItem);
        database.getCartItems().add(mockCartItem);
        assertEquals(4, database.getAllCartItems().size());
    }

    @Disabled
    @Test
    void shouldReturn2CartItems() {
        //TODO how do you even ?
        when(mockCartItem.getCart()).thenReturn(mockCart);
        database.getCartItems().add(mockCartItem);
        database.getCartItems().add(mockCartItem);
        database.getCartItems().add(mockCartItem);
        database.getCartItems().add(mockCartItem);
        assertEquals(2, database.getAllCartItemsForCartId(mockCart).size());
    }

    @Test
    void shouldIncreaseNextIdAfterSave() {
        Long idBefore = database.getNextId();
        database.save(mockCartItem);
        Long idAfter = database.getNextId();
        assertEquals(1, idAfter - idBefore);
    }

}