package shop.core.database;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import shop.core.database.in_memory.InMemoryCartRepositoryImpl;
import shop.core.domain.cart.Cart;
import shop.core.domain.cart.CartStatus;
import shop.core.domain.user.User;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class InMemoryCartRepositoryImplTest {

    @Mock
    private Cart mockCart;
    @Mock
    private User mockUser;

    @InjectMocks
    private InMemoryCartRepositoryImpl database;

    @Test
    void shouldIncreaseInSizeAfterSave() {
        database.save(mockCart);
        assertEquals(1, database.getCarts().size());
    }

    @Test
    void shouldReturnFoundOpenCart() {
        when(mockCart.getUser()).thenReturn(mockUser);
        when(mockCart.getStatus()).thenReturn("OPEN");
        database.getCarts().add(mockCart);
        assertTrue(database.findOpenCartForUserId(mockUser).isPresent());
    }

    @Test
    void shouldReturnEmptyOptionalWhenNoOpenCarts() {
        when(mockCart.getUser()).thenReturn(mockUser);
        when(mockCart.getStatus()).thenReturn("CLOSED");
        database.getCarts().add(mockCart);
        assertTrue(database.findOpenCartForUserId(mockUser).isEmpty());
    }

    @Test
    void shouldChangeCartStatus() {
        when(mockCart.getId()).thenReturn(1L);
        database.getCarts().add(mockCart);
        database.changeCartStatus(1L, CartStatus.CLOSED);
        verify(mockCart).setStatus("CLOSED");
    }

    @Test
    void shouldChangeLastActionDate() {
        when(mockCart.getId()).thenReturn(1L);
        database.getCarts().add(mockCart);
        LocalDateTime localDateTime = LocalDateTime.now();
        database.changeLastActionDate(1L, localDateTime);
        verify(mockCart).setLastUpdate(localDateTime);
    }

    @Test
    void shouldReturn3Carts() {
        database.getCarts().add(mockCart);
        database.getCarts().add(mockCart);
        database.getCarts().add(mockCart);
        assertEquals(3, database.getAllCarts().size());
    }

    @Test
    void shouldIncreaseNextIdAfterSave() {
        Long idBefore = database.getNextId();
        database.save(mockCart);
        Long idAfter = database.getNextId();
        assertEquals(1, idAfter - idBefore);
    }

}
