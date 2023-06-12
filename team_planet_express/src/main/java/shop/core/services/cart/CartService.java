package shop.core.services.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.core.database.CartItemRepository;
import shop.core.services.validators.universal.system.DatabaseAccessProvider;

import java.math.BigDecimal;

@Service
public class CartService {

    @Autowired
    private CartItemRepository cartItemRepository;
    @Autowired
    private DatabaseAccessProvider databaseAccessProvider;

    public BigDecimal getSum(Long cartId) {
        return cartItemRepository.getAllCartItemsForCartId(cartId)
                .stream()
                .map(cartItem -> databaseAccessProvider.getItemById(cartItem.getItem().getId()).getPrice()
                        .multiply(new BigDecimal(cartItem.getOrderedQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
