package shop.core.dtos;

import lombok.Value;

import java.math.BigDecimal;

@Value
public class CartItemDto {

    String userLogin;
    String itemName;
    BigDecimal itemPrice;
    Integer orderedQuantity;

}
