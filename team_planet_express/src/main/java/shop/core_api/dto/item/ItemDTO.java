package shop.core_api.dto.item;

import lombok.Data;
import lombok.NoArgsConstructor;
import shop.web_ui.components.Money;

@Data
@NoArgsConstructor
public class ItemDTO {

    private Long id;
    private String name;
    private Money price;
    private Integer availableQuantity;
    private byte[] itemPicture;

    public ItemDTO(String name, Money price, Integer availableQuantity) {
        this.name = name;
        this.price = price;
        this.availableQuantity = availableQuantity;
    }

}
