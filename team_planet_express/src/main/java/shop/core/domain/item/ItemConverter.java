package shop.core.domain.item;

import shop.core_api.dto.item.ItemDTO;
import shop.core_api.dto.item.Money;

public class ItemConverter {
    public static Item toItem(ItemDTO itemDTO) {
        Item item = new Item();
        if (itemDTO.getId() != null)
            item.setId(itemDTO.getId());
        if (itemDTO.getName() != null)
            item.setName(itemDTO.getName());
        if (itemDTO.getPrice() != null)
            item.setPrice(itemDTO.getPrice().getAmount());
        if (itemDTO.getAvailableQuantity() != null)
            item.setAvailableQuantity(itemDTO.getAvailableQuantity());
        if (itemDTO.getItemPicture() != null)
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
