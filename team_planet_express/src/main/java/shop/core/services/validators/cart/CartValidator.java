package shop.core.services.validators.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shop.core.database.Database;
import shop.core.responses.CoreError;

import java.util.Optional;

@Component
public class CartValidator {

    private static final String FIELD_BUTTON = "button";
    private static final String ERROR_NO_OPEN_CART = "You do not have an open cart.";

    @Autowired
    private Database database;

    public Optional<CoreError> validateOpenCartExistsForUserId(Long userId) {
        return (database.accessCartDatabase().findOpenCartForUserId(userId).isEmpty())
                ? Optional.of(new CoreError(FIELD_BUTTON, ERROR_NO_OPEN_CART))
                : Optional.empty();
    }

}
