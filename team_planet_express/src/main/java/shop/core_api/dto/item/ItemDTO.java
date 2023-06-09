package shop.core_api.dto.item;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import shop.core.domain.item.Item;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDTO {

    private Long id;
    private String name;
    private Money price;
    private Integer availableQuantity;
    private byte[] itemPicture;

    public Item toItem() {
        return ItemDTO.toItem(this);
    }

    public static Item toItem(ItemDTO itemDTO) {
        Item item = new Item();
        item.setId(itemDTO.getId());
        item.setName(itemDTO.getName());
        item.setPrice(itemDTO.getPrice().getAmount());
        item.setAvailableQuantity(itemDTO.getAvailableQuantity());
        item.setItemPicture(itemDTO.getItemPicture());
        return item;
    }

    public static ItemDTO of(Item item) {
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
