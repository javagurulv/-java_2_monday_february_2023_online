package shop.core.support;

import lombok.Value;

import java.math.BigDecimal;

@Value
@Deprecated
public class CartItemForList {

    String itemName;
    BigDecimal price;
    Integer orderedQuantity;

}
