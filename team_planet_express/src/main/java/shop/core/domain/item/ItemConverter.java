package shop.core.domain.item;

import shop.core_api.dto.item.ItemDTO;
import shop.core_api.dto.item.Money;

public class ItemConverter {
    public static Item toItem(ItemDTO itemDTO) {
        Item item = new Item();
        item.setId(itemDTO.getId());
        item.setName(itemDTO.getName());
        item.setPrice(itemDTO.getPrice().getAmount());
        item.setAvailableQuantity(itemDTO.getAvailableQuantity());
        item.setItemPicture(itemDTO.getItemPicture());
        return item;
    }

    public static ItemDTO toItemDTO(Item item) {
        ItemDTO itemDTO = new ItemDTO(
                item.getId(),
                item.getName(),
                Money.dollars(item.getPrice()),
                item.getAvailableQuantity(),
                item.getItemPicture()
        );
        return itemDTO;
    }
}
