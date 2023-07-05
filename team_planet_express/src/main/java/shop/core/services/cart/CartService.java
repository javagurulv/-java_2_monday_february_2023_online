package shop.core.services.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shop.core.database.CartItemRepository;

import java.math.BigDecimal;

@Service
public class CartService {

    @Autowired
    private CartItemRepository cartItemRepository;

    public BigDecimal getSum(Long cartId) {
        return cartItemRepository.findAll()
                .stream()
                .map(cartItem -> cartItem.getItem().getPrice()
                        .multiply(new BigDecimal(cartItem.getOrderedQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
