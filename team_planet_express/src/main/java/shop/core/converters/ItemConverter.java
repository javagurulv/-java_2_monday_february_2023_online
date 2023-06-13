package shop.core.converters;

import org.springframework.stereotype.Component;
import shop.core.domain.Item;
import shop.core.dtos.ItemDto;

import java.util.List;

@Component
public class ItemConverter {

    public ItemDto toItemDto(Item item) {
        return new ItemDto(
                item.getId(),
                item.getName(),
                item.getPrice(),
                item.getAvailableQuantity());
    }

    public List<ItemDto> toItemDto(List<Item> items) {
        return items.stream()
                .map(this::toItemDto)
                .toList();
    }

}
