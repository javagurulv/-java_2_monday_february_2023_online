package shop.console_ui.item_list;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import shop.core.domain.item.Item;
import shop.core.domain.user.UserRole;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class ItemStringProviderTest {

    @Mock
    private Item mockItem;

    @InjectMocks
    private ItemStringProvider itemStringProvider;

    @Test
    void shouldReturnStringWithIdForManager() {
        String result = itemStringProvider.get(mockItem, UserRole.MANAGER);
        assertTrue(result.contains("id"));
    }

    @Test
    void shouldReturnWithoutIdForNonManager() {
        String result = itemStringProvider.get(mockItem, UserRole.CUSTOMER);
        assertFalse(result.contains("id"));
    }

    @Test
    void shouldReturnWithoutIdForGuest() {
        String result = itemStringProvider.get(mockItem, UserRole.GUEST);
        assertFalse(result.contains("id"));
    }

}
