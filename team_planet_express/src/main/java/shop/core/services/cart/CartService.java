package shop.core.services.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shop.core.database.Database;
import shop.core.services.validators.universal.system.DatabaseAccessValidator;

import java.math.BigDecimal;

@Component
public class CartService {

    @Autowired
    private Database database;
    @Autowired
    private DatabaseAccessValidator databaseAccessValidator;

    public BigDecimal getSum(Long cartId) {
        return database.accessCartItemDatabase().getAllCartItemsForCartId(cartId)
                .stream()
                .map(cartItem -> databaseAccessValidator.getItemById(cartItem.getItemId()).getPrice()
                        .multiply(new BigDecimal(cartItem.getOrderedQuantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

}
