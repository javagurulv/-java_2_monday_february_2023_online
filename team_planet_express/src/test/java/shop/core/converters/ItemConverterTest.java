package shop.core.converters;

import org.junit.jupiter.api.Test;
import shop.core.domain.Item;
import shop.core.dtos.ItemDto;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ItemConverterTest {

    private static final Long ID_1 = 1L;
    private static final String NAME_1 = "name1";
    private static final BigDecimal PRICE_1 = new BigDecimal("10.10");
    private static final Integer AVAILABLE_QUANTITY_1 = 1;
    private static final Long ID_2 = 2L;
    private static final String NAME_2 = "name2";
    private static final BigDecimal PRICE_2 = new BigDecimal("20.20");
    private static final Integer AVAILABLE_QUANTITY_2 = 2;

    private final ItemConverter itemConverter = new ItemConverter();

    @Test
    void shouldConvert() {
        ItemDto itemDto = itemConverter.toItemDto(createItem(
                ID_1, NAME_1, PRICE_1, AVAILABLE_QUANTITY_1));
        assertEquals(ID_1, itemDto.getId());
        assertEquals(NAME_1, itemDto.getName());
        assertEquals(PRICE_1, itemDto.getPrice());
        assertEquals(AVAILABLE_QUANTITY_1, itemDto.getAvailableQuantity());
    }

    @Test
    void shouldConvertAll() {
        List<ItemDto> itemDtos = itemConverter.toItemDto(List.of(
                createItem(ID_1, NAME_1, PRICE_1, AVAILABLE_QUANTITY_1),
                createItem(ID_2, NAME_2, PRICE_2, AVAILABLE_QUANTITY_2)
        ));
        assertEquals(ID_1, itemDtos.get(0).getId());
        assertEquals(NAME_1, itemDtos.get(0).getName());
        assertEquals(PRICE_1, itemDtos.get(0).getPrice());
        assertEquals(AVAILABLE_QUANTITY_1, itemDtos.get(0).getAvailableQuantity());
        assertEquals(ID_2, itemDtos.get(1).getId());
        assertEquals(NAME_2, itemDtos.get(1).getName());
        assertEquals(PRICE_2, itemDtos.get(1).getPrice());
        assertEquals(AVAILABLE_QUANTITY_2, itemDtos.get(1).getAvailableQuantity());
    }

    private Item createItem(Long id, String name, BigDecimal price, Integer availableQuantity) {
        Item item = new Item();
        item.setId(id);
        item.setName(name);
        item.setPrice(price);
        item.setAvailableQuantity(availableQuantity);
        return item;
    }

}
