package shop.core.support;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class CartItemForList {

    String itemName;
    BigDecimal price;
    Integer orderedQuantity;

}
