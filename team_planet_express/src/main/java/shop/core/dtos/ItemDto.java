package shop.core.dtos;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class ItemDto {

    Long id;
    String name;
    BigDecimal price;
    Integer availableQuantity;

}
