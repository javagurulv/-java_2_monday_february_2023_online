package shop.core.services.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shop.core.database.jpa.JpaCartItemRepository;
import shop.core.domain.Cart;
import shop.core.services.validators.universal.system.RepositoryAccessValidator;

import java.math.BigDecimal;

@Component
public class CartService {

    @Autowired
    private JpaCartItemRepository cartItemRepository;
    @Autowired
    private RepositoryAccessValidator repositoryAccessValidator;

    public BigDecimal getSum(Cart cart) {
        return cartItemRepository.findByCart(cart)
                .stream()
                .map(cartItem -> repositoryAccessValidator.getItemById(cartItem.getItem().getId()).getPrice()
                        .multiply(new BigDecimal(cartItem.getOrderedQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
