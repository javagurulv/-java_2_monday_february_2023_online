package shop.console_ui.item_list;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import shop.core.dtos.ItemDto;

import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class ItemStringProviderTest {

    @Mock
    private ItemDto mockItemDto;

    @InjectMocks
    private ItemStringProvider itemStringProvider;

    @Test
    void shouldReturnStringWithId() {
        String result = itemStringProvider.get(mockItemDto);
        assertTrue(result.contains("id"));
    }

}
