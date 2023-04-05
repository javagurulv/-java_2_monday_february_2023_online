package core.services.fake.fake_item_generator;

import Shop.core.domain.item.Item;
import Shop.core.services.fake.fake_item_generator.HardcodedItemGeneratorImpl;
import Shop.core.services.fake.fake_item_generator.ItemGenerator;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class HardcodedItemGeneratorImplTest {

    private final ItemGenerator itemGenerator = new HardcodedItemGeneratorImpl();

    @Test
    void shouldCreateListOf10Items() {
        List<Item> items = itemGenerator.createItems();
        assertEquals(10, items.size());
        assertTrue(items.get(0).getName().contains("Stop"));
    }

}
