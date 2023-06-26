package shop.core.converters;

import org.junit.jupiter.api.Test;
import shop.core.domain.Cart;
import shop.core.domain.CartItem;
import shop.core.domain.Item;
import shop.core.domain.User;
import shop.core.dtos.CartItemDto;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CartItemConverterTest {

    private static final String USER_LOGIN_1 = "userLogin1";
    private static final String ITEM_NAME_1 = "itemName1";
    private static final BigDecimal ITEM_PRICE_1 = new BigDecimal("10.10");
    private static final Integer ORDERED_QUANTITY_1 = 1;
    private static final String USER_LOGIN_2 = "userLogin2";
    private static final String ITEM_NAME_2 = "itemName2";
    private static final BigDecimal ITEM_PRICE_2 = new BigDecimal("20.20");
    private static final Integer ORDERED_QUANTITY_2 = 2;

    private final CartItemConverter cartItemConverter = new CartItemConverter();

    @Test
    void shouldConvert() {
        CartItemDto cartItemDto = cartItemConverter.toCartItemDto(createCartItem(
                USER_LOGIN_1, ITEM_NAME_1, ITEM_PRICE_1, ORDERED_QUANTITY_1));
        assertEquals(USER_LOGIN_1, cartItemDto.getUserLogin());
        assertEquals(ITEM_NAME_1, cartItemDto.getItemName());
        assertEquals(ITEM_PRICE_1, cartItemDto.getItemPrice());
        assertEquals(ORDERED_QUANTITY_1, cartItemDto.getOrderedQuantity());
    }

    @Test
    void shouldConvertAll() {
        List<CartItemDto> cartItemDtos = cartItemConverter.toCartItemDto(List.of(
                createCartItem(USER_LOGIN_1, ITEM_NAME_1, ITEM_PRICE_1, ORDERED_QUANTITY_1),
                createCartItem(USER_LOGIN_2, ITEM_NAME_2, ITEM_PRICE_2, ORDERED_QUANTITY_2)
        ));
        assertEquals(USER_LOGIN_1, cartItemDtos.get(0).getUserLogin());
        assertEquals(ITEM_NAME_1, cartItemDtos.get(0).getItemName());
        assertEquals(ITEM_PRICE_1, cartItemDtos.get(0).getItemPrice());
        assertEquals(ORDERED_QUANTITY_1, cartItemDtos.get(0).getOrderedQuantity());
        assertEquals(USER_LOGIN_2, cartItemDtos.get(1).getUserLogin());
        assertEquals(ITEM_NAME_2, cartItemDtos.get(1).getItemName());
        assertEquals(ITEM_PRICE_2, cartItemDtos.get(1).getItemPrice());
        assertEquals(ORDERED_QUANTITY_2, cartItemDtos.get(1).getOrderedQuantity());
    }

    private CartItem createCartItem(String userLogin, String itemName, BigDecimal itemPrice, Integer orderedQuantity) {
        User user = new User();
        user.setLogin(userLogin);
        Cart cart = new Cart();
        cart.setUser(user);
        Item item = new Item();
        item.setName(itemName);
        item.setPrice(itemPrice);
        return new CartItem(cart, item, orderedQuantity);
    }

}
