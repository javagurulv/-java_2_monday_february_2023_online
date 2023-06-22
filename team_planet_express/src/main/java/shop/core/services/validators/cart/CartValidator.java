package shop.core.services.validators.cart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import shop.core.database.jpa.JpaCartRepository;
import shop.core.domain.User;
import shop.core.error_code_processing.ErrorProcessor;
import shop.core.responses.CoreError;

import java.util.Optional;

@Component
public class CartValidator {

    private static final String FIELD_BUTTON = "button";
    private static final String ERROR_NO_OPEN_CART = "VDT-CRT-NOC";

    @Autowired
    private JpaCartRepository cartRepository;
    @Autowired
    private ErrorProcessor errorProcessor;

    public Optional<CoreError> validateOpenCartExistsForUser(User user) {
        return (cartRepository.findOpenCartByUser(user).isEmpty())
                ? Optional.of(errorProcessor.getCoreError(FIELD_BUTTON, ERROR_NO_OPEN_CART))
                : Optional.empty();
    }

}
